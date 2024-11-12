package com.web.SpringBootRestDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.SpringBootRestDemo.entity.Customer;
import com.web.SpringBootRestDemo.repo.CustomerRepo;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public Customer getCustomerById(int id){
        return customerRepo.findById(id)
                .orElseThrow(()->  new EntityNotFoundException("customer with "+id+" does not exists"));
    }
    public Customer insertCustomer(Customer customer){
        if(customerRepo.existsById(customer.getCustid()))
            throw new EntityExistsException("Cannot add "+customer.getCustid()+" already exists");

        return customerRepo.save(customer);
    }
    public Customer getCustomerProfileAndInvoicesById(int id){
        Customer customer = customerRepo.findById(id)
                .orElseThrow(()->  new EntityNotFoundException("customer with "+id+" does not exists"));
        System.out.println(customer.getInvoices());
        return  customer;
    }

}
