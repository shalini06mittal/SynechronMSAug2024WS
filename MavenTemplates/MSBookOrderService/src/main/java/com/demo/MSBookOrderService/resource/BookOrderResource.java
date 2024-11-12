package com.demo.MSBookOrderService.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.demo.MSBookOrderService.dto.RespoonseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
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
		List<BookOrder> orders = new ArrayList<>();
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
	public RespoonseWrapper getBooksOrderedByUser1(@PathVariable String email)
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
		RespoonseWrapper ob = new RespoonseWrapper();
		ob.setOrders(orders);
		ob.setBooks(Arrays.asList("book1", "book2"));
		return ob;
	}
	
}
