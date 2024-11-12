package com.demo.MSBookService.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.MSBookService.model.Book;

public interface BookRepository extends CrudRepository<Book, String>{

}
