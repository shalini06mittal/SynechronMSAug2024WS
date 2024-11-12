package com.demo.MSBookCatalogService.service;

import java.util.List;

import com.demo.MSBookCatalogService.model.OrderWrapper;
import com.demo.MSBookCatalogService.model.BookOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name="MSBOOKORDERSERVICE")
public interface BookOrderFeignClient {

	
	@RequestMapping("/orders/{email}")
	public List<BookOrder> getBookOrderDetails(@PathVariable String email);
	@GetMapping("/orders/wrapper/{email}")
	public OrderWrapper getBookOrderDetailsWrapper(@PathVariable String email);
}
