package com.techgatha.box8.MSBox8Service.service;

import com.techgatha.box8.MSBox8Service.client.RestaurantServiceClient;
import com.techgatha.box8.MSBox8Service.dto.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwiggyAppService {

    @Autowired
    private RestaurantServiceClient restaurantServiceClient;

    public String greeting() {
        return "Welcome to Swiggy App Service";
    }

    public OrderResponseDTO checkOrderStatus(String orderId, String token) {
        return restaurantServiceClient.fetchOrderStatus(orderId,  token);
    }
}
