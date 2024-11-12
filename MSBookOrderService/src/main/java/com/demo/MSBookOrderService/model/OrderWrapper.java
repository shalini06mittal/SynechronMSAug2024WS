package com.demo.MSBookOrderService.model;

import java.util.List;

public class OrderWrapper {

    private List<BookOrder> orders;
    private String member;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BookOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<BookOrder> orders) {
        this.orders = orders;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
