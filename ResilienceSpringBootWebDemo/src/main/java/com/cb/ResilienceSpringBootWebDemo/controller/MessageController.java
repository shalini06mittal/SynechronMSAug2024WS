package com.cb.ResilienceSpringBootWebDemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RefreshScope
public class MessageController {

    @Value("${msg: Config not working fro msg}")
    private String message;

    @Value("${desc: Config not working fro desc}")
    private String description;
    @GetMapping
    public Map<String, String> getMessage(){
        Map<String, String> map = new HashMap<>();
        map.put("msg",message);
        map.put("desc",description);
        return map;
    }

}
