package com.config.ConfigServerClient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RefreshScope
public class MessageController {

    @Value("${msg: Config not working}")
    private String message;
    @GetMapping
    public String getMessage(){
        return message;
    }

}
