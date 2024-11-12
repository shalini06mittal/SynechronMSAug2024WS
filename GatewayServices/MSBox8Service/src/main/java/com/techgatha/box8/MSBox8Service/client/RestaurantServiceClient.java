package com.techgatha.box8.MSBox8Service.client;

import com.techgatha.box8.MSBox8Service.dto.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestaurantServiceClient {
    @Autowired
    private RestTemplate template;

    public OrderResponseDTO fetchOrderStatus(String orderId, String token) {

        return template.getForObject("http://GATEWAY-SERVICE/restaurant/orders/status/" + orderId+"token="+token, OrderResponseDTO.class);
    }
}
