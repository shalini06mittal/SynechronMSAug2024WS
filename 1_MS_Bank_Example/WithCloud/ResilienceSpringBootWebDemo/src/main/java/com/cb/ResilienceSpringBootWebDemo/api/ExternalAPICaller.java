package com.cb.ResilienceSpringBootWebDemo.api;

import org.springframework.stereotype.Component;

@Component
public class ExternalAPICaller {
    public ExternalAPICaller() {
        System.out.println("External API caller");
    }

    public String callApi() {
        return "external API call";
    }
    public String callApi(int id) {
            if(id == 1 || id==2 || id==3)
                throw new RuntimeException(id + " hence failed");

        return "external API call with exception";
    }
    public String callApiSlowRate(long delay) {
        try {
            Thread.sleep(delay);
        }
        catch (InterruptedException ignore) {
        }
        return "external API call SLOW";
    }
    public String callApiRetry(String message) {
         if(message.equals("error"))
             throw  new RuntimeException("error");
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
