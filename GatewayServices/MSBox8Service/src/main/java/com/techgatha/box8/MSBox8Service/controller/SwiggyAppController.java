package com.techgatha.box8.MSBox8Service.controller;

import com.techgatha.box8.MSBox8Service.dto.OrderResponseDTO;
import com.techgatha.box8.MSBox8Service.service.SwiggyAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/box8")
public class SwiggyAppController {

    private static Logger log = LoggerFactory.getLogger(SwiggyAppController.class);
    @Autowired
    private SwiggyAppService service;

    @GetMapping("/home")
    public String greetingMessage() {
        return service.greeting();
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO checkOrderStatus(
            @PathVariable String orderId, //@RequestHeader("username") String username,
            @RequestHeader("token") String token) {
       // System.out.println("Loggedin user "+username);
//        log.info("Handling order for swiggy");
        return service.checkOrderStatus(orderId, token);
    }
}
