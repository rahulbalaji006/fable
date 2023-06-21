package com.fable.spring.scheduler;

import com.fable.spring.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserLogScheduler {

    @Autowired
    UserLogService userLogService;

    @Scheduled(fixedDelay = 1000)
    public void saveUserLogToDb(){
        userLogService.saveUserLog();
    }
}
