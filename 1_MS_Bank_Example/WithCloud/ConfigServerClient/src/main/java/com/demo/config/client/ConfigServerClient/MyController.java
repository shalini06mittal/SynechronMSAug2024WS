package com.demo.config.client.ConfigServerClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MyController {

    @Value("${msg: Did not get data from repo}")
    private  String message;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @GetMapping("/greet")
    public String sendMessage(){
        return  message;
    }

    @GetMapping("/contact")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
}
