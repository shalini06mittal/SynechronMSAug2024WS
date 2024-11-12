package com.cb.ResilienceSpringBootWebDemo.api;

import org.springframework.stereotype.Component;

@Component
public class ExternalAPICaller {

    public String callApi(){
        return "external API called";
    }

    public String callApiThrowsException(int id){
        System.out.println("service called with id "+id);
        if(id==1)
            throw new ArrayIndexOutOfBoundsException(id+" array index out pof bounds failed");
        if(id==2)
            throw new ArithmeticException("arithmetic for id "+id);
        if(id==3)
            throw new StringIndexOutOfBoundsException(id+" sting index out of bounds");
        return "external API called with exception for id "+id;
    }

    public String callApiSlowRate(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignore) {
        }
        return "external API call SLOW";
    }

    int c = 1;
    public String callApiRetry(String message) {
        if(c<=2)
            if(message.equals("error")) {
                c++;
                throw new RuntimeException("error");
            }
        if(c!=1) c=1;
        return "external API call Retry";
    }


    public String callApiWithDelay(long delay) {
        String result ="external API call with delay";
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignore) {
            throw new RuntimeException(ignore.getMessage());
        }
        return result;
    }
}
