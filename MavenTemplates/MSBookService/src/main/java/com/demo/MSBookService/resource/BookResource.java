package com.demo.MSBookService.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.MSBookService.model.Book;
import com.demo.MSBookService.repository.BookRepository;

@RestController
@RequestMapping("/books")
public class BookResource {

	@Autowired
	private BookRepository repository;
	
	@GetMapping("/{bookid}")
	public Book getBook(@PathVariable String bookid)
	{
		return repository.findById(bookid).get();
	}
}
