package com.web.SpringBootRestDemo.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.SpringBootRestDemo.entity.Invoice;

public interface InvoiceRepo extends JpaRepository<Invoice,Integer> {
    public List<Invoice> findAllByCustomerCustid(int id);
}
