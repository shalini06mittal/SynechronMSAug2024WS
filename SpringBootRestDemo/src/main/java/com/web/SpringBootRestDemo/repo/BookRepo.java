package com.web.SpringBootRestDemo.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.SpringBootRestDemo.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {

    List<Book> findByAuthor(String author);
    //public List<Book> findByPrice(double price);
}
