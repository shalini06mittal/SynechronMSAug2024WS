package com.demo.MSBookCatalogService.service;

import com.demo.MSBookCatalogService.model.BookCatalog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@FeignClient(name="MSBOOKSERVICE")
public interface BookFeignClient {

	
	@RequestMapping("/books/{bookid}")
	public BookCatalog getBookDetails(@PathVariable String bookid);
}
