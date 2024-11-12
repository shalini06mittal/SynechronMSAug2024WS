package com.techgatha.restaurant.MSRestaurantService.controller;

import com.techgatha.restaurant.MSRestaurantService.dto.OrderResponseDTO;
import com.techgatha.restaurant.MSRestaurantService.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @GetMapping
    public String greetingMessage() {
        return service.greeting();
    }

    @GetMapping("/orders/status/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable String orderId){
            //, @RequestHeader("username") String username) {
        //System.out.println("Loggedin user "+username);
        System.out.println(orderId);
        return service.getOrder(orderId);
    }
}
