package com.demo.MSBookOrderService.dto;

import com.demo.MSBookOrderService.model.BookOrder;

import java.util.List;

public class RespoonseWrapper {

    private List<BookOrder> orders;
    private List<String> books;

    public List<BookOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<BookOrder> orders) {
        this.orders = orders;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}
