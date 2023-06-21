package com.fable.spring.service;

import com.fable.spring.model.entity.ConfigEntity;
import com.fable.spring.repository.ConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfigService {

    @Autowired ConfigRepo configRepo;

    public String saveConfig(String key, LocalDateTime value){
        configRepo.save(ConfigEntity.builder()
                .key(key)
                .value(value)
                .build());
        return "success";
    }

    public LocalDateTime getConfigValue(String key){
        Optional<ConfigEntity> optionalConfig = configRepo.findByKey(key);
        if(optionalConfig.isPresent()) {
            return optionalConfig.get().getValue();
        }
        return null;
    }

}
