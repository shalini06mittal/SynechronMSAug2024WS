package com.demo.MSBookOrderService.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.demo.MSBookOrderService.model.OrderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.MSBookOrderService.model.BookOrder;
import com.demo.MSBookOrderService.repository.BookOrderRepository;

@RestController
@RequestMapping("/orders")
public class BookOrderResource {

	@Autowired
	BookOrderRepository repository;

	
	@GetMapping("/{email}")
	public List<BookOrder> getBooksOrderedByUser(@PathVariable String email)
	{
		//List<BookOrder> orders = new ArrayList<>();
		return repository.findByEmail(email).stream()
				.map(order -> 
				{
					BookOrder obj = new BookOrder();
					obj.setBookid(order.getBookid());
					obj.setDatetime(order.getDatetime());
					return obj;
		}).collect(Collectors.toList());
	}

	@GetMapping("/wrapper/{email}")
	public OrderWrapper getBooksOrderedByUserUsingWrapper(@PathVariable String email)
	{
		List<BookOrder> orders = new ArrayList<>();
		orders = repository.findByEmail(email).stream()
				.map(order ->
				{
					BookOrder obj = new BookOrder();
					obj.setBookid(order.getBookid());
					obj.setDatetime(order.getDatetime());
					return obj;
				}).collect(Collectors.toList());
		OrderWrapper wrapper =new OrderWrapper();
		wrapper.setOrders(orders);
		wrapper.setMember("GOLD");
		wrapper.setMessage("some message");
		return  wrapper;
	}
	
}
