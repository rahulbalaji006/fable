package com.fable.spring.controller;

import com.fable.spring.model.dto.UserLogDto;
import com.fable.spring.service.UserLogService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLogController {

    private final UserLogService userLogService;

    public UserLogController(UserLogService userLogService) {
        this.userLogService = userLogService;
    }

    @PostMapping("/log")
    public String postUserLog(@NonNull @RequestBody UserLogDto userLogDto){
        userLogService.postUserLog(userLogDto);
        return "success!";
    }

    @PostMapping("/log/sync")
    public String saveUserLog(){
        userLogService.saveUserLog();
        return "success!";
    }
}
