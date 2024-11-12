package com.demo.MSBookCatalogService.service;

import java.util.List;
import java.util.stream.Collectors;

import com.demo.MSBookCatalogService.model.CatalogWrapper;
import com.demo.MSBookCatalogService.model.OrderWrapper;
import com.demo.MSBookCatalogService.model.BookOrder;
import com.demo.MSBookCatalogService.model.BookCatalog;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;


@Service
public class BookCatalogService {

	@Autowired
	BookOrderFeignClient bookOrderFeignClient;
	
	@Autowired
	BookFeignClient bookFeignClient;
	
	public List<BookCatalog> getBooksOrdered(String email)
	{	
		List<BookOrder> orders = this.bookOrderFeignClient.getBookOrderDetails(email);
		
		return orders.stream()
		.map(wrapper ->{
			System.out.println(wrapper.getBookid());
			BookCatalog catalog = this.bookFeignClient.getBookDetails(wrapper.getBookid());
			//catalog.setEmail(email);
			catalog.setDatetime(wrapper.getDatetime());
			return catalog;
		}).collect(Collectors.toList());
	}
	public CatalogWrapper getBooksOrderedwrapper(String email)
	{
		OrderWrapper dto = this.bookOrderFeignClient.getBookOrderDetailsWrapper(email);
		List<BookOrder> orders = dto.getOrders();
		System.out.println(dto.getMember());
		List<BookCatalog> catalogs = orders.stream()
				.map(wrapper ->{
					System.out.println(wrapper.getBookid());
					BookCatalog catalog = this.bookFeignClient.getBookDetails(wrapper.getBookid());
					catalog.setDatetime(wrapper.getDatetime());
					return catalog;
				}).collect(Collectors.toList());
		CatalogWrapper wrapper = new CatalogWrapper();
		wrapper.setBooks(catalogs);
		wrapper.setEmail(email);
		wrapper.setMember(dto.getMember());
		return  wrapper;
	}
}
