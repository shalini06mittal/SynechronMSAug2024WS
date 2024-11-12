package com.demo.MSBookCatalogService.service;

import java.util.List;

import com.demo.MSBookCatalogService.model.OrderWrapper;
import com.demo.MSBookCatalogService.model.OrdersResponseWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name="MSBOOKORDERSERVICE")
public interface BookOrderFeignClient {

	
	@RequestMapping("/orders/{email}")
	public List<OrderWrapper> getBookOrderDetails(@PathVariable String email);

	@RequestMapping("/orders/wrapper/{email}")
	public OrdersResponseWrapper getBookOrderDetailsWithWrapper(@PathVariable String email);
}
