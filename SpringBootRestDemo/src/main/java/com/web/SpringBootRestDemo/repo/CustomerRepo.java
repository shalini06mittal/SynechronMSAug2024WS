package com.web.SpringBootRestDemo.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.web.SpringBootRestDemo.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
}
