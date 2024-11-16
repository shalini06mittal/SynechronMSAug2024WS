package com.demo.MSAccountDemo.service;


import com.demo.MSAccountDemo.dto.CustomerDetailsDto;

public interface ICustomersService {

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    CustomerDetailsDto fetchCustomerDetails(String  correlationId,String mobileNumber);
//    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
