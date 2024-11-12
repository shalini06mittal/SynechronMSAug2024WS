package com.demo.MSBookCatalogService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class CatalogWrapper {

    private List<BookCatalog> books;
    private String email;

    private String member;

    public List<BookCatalog> getBooks() {
        return books;
    }

    public void setBooks(List<BookCatalog> books) {
        this.books = books;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
