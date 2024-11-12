package com.techgatha.cb.HystrixCircuitBreakerDemo.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MyService {
    @HystrixCommand(fallbackMethod = "defaultDoSomething",
    commandKey = "mykey")
    public void doSomething(int input) {
        System.out.println("input: " + input);
        //in case of exception fallbackMethod is called
       
        System.out.println("output: " + 10 / input);
    }

    public void defaultDoSomething(int input) {
        System.out.println("in default method, the input number: " + input);
    }
    
    
    
    
    

//    @HystrixCommand(fallbackMethod = "defaultDoSomething")
//    public void doSomething2(int input) {
//        try {
//            TimeUnit.MILLISECONDS.sleep(1500);// timeout scenario
//        } catch (InterruptedException e) {
//            return;
//        }
//        System.out.println("input: " + input);
//        System.out.println("output: " + 10 / input);
//    }
}
/*
commandProperties = {
@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
}*/