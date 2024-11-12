package com.currex.MSCurrencyConverterService.config;

import com.currex.MSCurrencyConverterService.exception.CustomErrorDecaode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FeignConfig {

    @Bean
    public CustomErrorDecaode customErrorDecaode() {
        System.out.println("custom error");
        return new CustomErrorDecaode();
    }
}
