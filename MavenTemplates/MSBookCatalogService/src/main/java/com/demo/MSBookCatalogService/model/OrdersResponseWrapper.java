package com.demo.MSBookCatalogService.model;

import org.springframework.core.annotation.Order;

import java.util.List;

public class OrdersResponseWrapper {

    private List<OrderWrapper> orders;

    public List<OrderWrapper> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderWrapper> orders) {
        this.orders = orders;
    }
}
