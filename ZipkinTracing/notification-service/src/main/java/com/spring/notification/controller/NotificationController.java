package com.spring.notification.controller;

import com.spring.notification.controller.ws.WSNotificationRequest;
import com.spring.notification.controller.ws.WSNotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 10/August/2020 By Author Eresh, Gorantla
 **/

@RestController
@RequestMapping("/api")
public class NotificationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//7a3242d7489f9fff - ms2
    //0949242189bfbe07
    @Autowired
    private RestTemplate template;
    @PostMapping("/notification")
    public ResponseEntity<WSNotificationResponse> sendNotification(@RequestBody WSNotificationRequest request) {

        if ("Email".equalsIgnoreCase(request.getChannel())) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                logger.error("Exception occurred in sending notification");
            }
        }
//        RestTemplate template1 = new RestTemplate();
//        System.out.println(template1.getForEntity("http://localhost:8085/ms2", String.class));
        return ResponseEntity.ok(new WSNotificationResponse());
    }
}
