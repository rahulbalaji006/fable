package com.fable.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/")
    public String home() {
        return "Gracias!";
    }

    @GetMapping("/health")
    public String getHealth() {
        return "Up!";
    }

}
