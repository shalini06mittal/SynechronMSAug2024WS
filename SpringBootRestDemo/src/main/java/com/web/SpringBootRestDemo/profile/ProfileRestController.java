package com.web.SpringBootRestDemo.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileRestController {

    @Autowired
    private EnvProfile envProfile;

    @GetMapping
    public String messages(){
        System.out.println("akjsdh");
        return envProfile.getMessage();
    }
}
