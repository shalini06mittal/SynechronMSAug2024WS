package com.demo.MSBookCatalogService.service;

import java.util.List;
import java.util.stream.Collectors;

import com.demo.MSBookCatalogService.model.OrderWrapper;
import com.demo.MSBookCatalogService.model.OrdersResponseWrapper;
import com.demo.MSBookCatalogService.model.UserCatalog;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;


@Service
public class BookCatalogService {

	@Autowired
	BookOrderFeignClient bookOrderFeignClient;
	
	@Autowired
	BookFeignClient bookFeignClient;
	
	public List<UserCatalog> getBooksOrdered(String email)
	{	
		List<OrderWrapper> orders = this.bookOrderFeignClient.getBookOrderDetails(email);
		
		return orders.stream()
		.map(wrapper ->{
			System.out.println(wrapper.getBookid());
			UserCatalog catalog = this.bookFeignClient.getBookDetails(wrapper.getBookid());
			catalog.setEmail(email);
			catalog.setDatetime(wrapper.getDatetime());
			return catalog;
		}).collect(Collectors.toList());
	}
	public List<UserCatalog> getBooksOrderedWrapper(String email)
	{
		OrdersResponseWrapper ordersWrapper = this.bookOrderFeignClient.getBookOrderDetailsWithWrapper(email);
		List<OrderWrapper> orders = ordersWrapper.getOrders();
		System.out.println(ordersWrapper);
		return orders.stream()
				.map(wrapper ->{
					System.out.println(wrapper.getBookid());
					UserCatalog catalog = this.bookFeignClient.getBookDetails(wrapper.getBookid());
					catalog.setEmail(email);
					catalog.setDatetime(wrapper.getDatetime());
					return catalog;
				}).collect(Collectors.toList());
	}
}
