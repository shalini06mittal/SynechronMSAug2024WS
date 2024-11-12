package com.demo.MSBookCatalogService.model;

import java.util.List;

public class OrderWrapper {

    private List<BookOrder> orders;
    private String member;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public List<BookOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<BookOrder> orders) {
        this.orders = orders;
    }
}
