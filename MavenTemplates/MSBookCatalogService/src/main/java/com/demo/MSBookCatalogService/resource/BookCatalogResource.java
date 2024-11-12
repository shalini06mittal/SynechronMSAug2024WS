package com.demo.MSBookCatalogService.resource;

import java.util.List;

import com.demo.MSBookCatalogService.model.UserCatalog;
import com.demo.MSBookCatalogService.service.BookCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/catalog")
public class BookCatalogResource {

	@Autowired
	private BookCatalogService service;
	
	@GetMapping("/{email}")
	public List<UserCatalog> getBooksPurchased(@PathVariable String email)
	{
		return service.getBooksOrdered(email);
	}
	
}
