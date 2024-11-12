package com.techgatha.restaurant.MSRestaurantService.service;

import com.techgatha.restaurant.MSRestaurantService.dao.RestaurantOrderDAO;
import com.techgatha.restaurant.MSRestaurantService.dto.OrderResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    Logger logger= LoggerFactory.getLogger(RestaurantService.class);
    @Autowired
    private RestaurantOrderDAO orderDAO;

    public String greeting() {
        return "Welcome to Swiggy Restaurant service";
    }

    public OrderResponseDTO getOrder(String orderId) {
        logger.info("Restaurant-Service Request : "+ orderId);
        return orderDAO.getOrders(orderId);
    }
}
