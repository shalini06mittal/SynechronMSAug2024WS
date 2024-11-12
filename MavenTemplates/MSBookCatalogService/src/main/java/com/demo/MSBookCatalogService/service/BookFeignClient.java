package com.demo.MSBookCatalogService.service;

import java.util.List;

import com.demo.MSBookCatalogService.model.UserCatalog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@FeignClient(name="MSBOOKSERVICE")
public interface BookFeignClient {

	
	@RequestMapping("/books/{bookid}")
	public UserCatalog getBookDetails(@PathVariable String bookid);
}
