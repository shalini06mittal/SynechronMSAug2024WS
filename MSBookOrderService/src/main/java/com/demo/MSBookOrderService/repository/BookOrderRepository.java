package com.demo.MSBookOrderService.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demo.MSBookOrderService.model.BookOrder;

public interface BookOrderRepository extends CrudRepository<BookOrder, Integer>{

	public List<BookOrder> findByEmail(String email);
	
}
