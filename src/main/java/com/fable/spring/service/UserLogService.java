package com.fable.spring.service;

import com.fable.spring.model.dto.UserLogDto;
import com.fable.spring.model.entity.UserLogEntity;
import com.fable.spring.repository.UserLogRepo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class UserLogService {

    @Autowired
    ConfigService configService;

    private final UserLogRepo userLogRepo;
    private final Path filePath = Path.of("/Users/oyo/Desktop/fable/userlog.txt");

    public UserLogService(UserLogRepo userLogRepo) {
        this.userLogRepo = userLogRepo;
    }

    public void postUserLog(UserLogDto userLogDto){
        JSONObject jsonObject = dtoToJson(userLogDto);
        try {
            FileWriter fr = new FileWriter(filePath.toFile(), true);
            fr.write(jsonObject.toJSONString());
            fr.write(System.lineSeparator());
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUserLog(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastUpdated = configService.getConfigValue("UserLog");
        long diff = lastUpdated != null ? lastUpdated.until(now, ChronoUnit.SECONDS) : 0;
        File file = new File(filePath.toUri());

        if(diff >= 30 || file.length() >= (1024*1024)) {
            JSONParser jsonParser = new JSONParser();
            try {
                List<UserLogEntity> userLogList = Files.lines(Paths.get(filePath.toUri()))
                        .map(jsonLine -> parseSingleLine(jsonParser, jsonLine))
                        .map(this::jsonToDto)
                        .map(this::dtoToEntity)
                        .toList();
                userLogRepo.saveAll(userLogList);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            configService.saveConfig("UserLog", now);
            file.delete();
        }
    }

    private JSONObject dtoToJson(UserLogDto userLogDto){
        JSONObject json = new JSONObject();
        json.put("id", userLogDto.getId());
        json.put("unixTs", userLogDto.getUnixTs());
        json.put("userId", userLogDto.getUserId());
        json.put("event", userLogDto.getEvent());
        return json;
    }

    private UserLogDto jsonToDto(JSONObject jsonObject){
        return UserLogDto.builder()
            .id((Long) jsonObject.get("id"))
            .unixTs((Long) jsonObject.get("unixTs"))
            .userId((Long) jsonObject.get("userId"))
            .event((String) jsonObject.get("event"))
            .build();
    }
    private UserLogEntity dtoToEntity(UserLogDto userLogDto){
        return UserLogEntity.builder()
                .logId(userLogDto.getId())
                .userId(userLogDto.getUserId())
                .event(userLogDto.getEvent())
                .unixTs(userLogDto.getUnixTs())
                .build();
    }

    private static JSONObject parseSingleLine(JSONParser parser, String jsonLine) {
        try {
            return (JSONObject) parser.parse(jsonLine);
        } catch (ParseException e) {
            throw new RuntimeException("Cannot parse json", e);
        }
    }
}
