package com.web.SpringBootRestDemo.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class DefaultEntity implements EnvProfile{

    @Value("${message}")
    private String message;

    public String getMessage(){
        return message;
    }

}
