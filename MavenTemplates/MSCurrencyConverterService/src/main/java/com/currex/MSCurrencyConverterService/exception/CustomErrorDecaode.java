package com.currex.MSCurrencyConverterService.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
// SpringDecoder
public class CustomErrorDecaode {//implements ErrorDecoder {

   // @Override
    public Exception decode(String methodKey, Response response) {
        // TODO Auto-generated method stub
        System.out.println("---------------------------------------");
        System.out.println(methodKey);
        System.out.println(response.status());
        System.out.println("---------------------------------------");
        return switch (response.status()) {
            case 404 -> new NotFoundException("Resource not found");
            case 500 -> new MyFeignClientException(HttpStatus.valueOf(response.status())
                    , String.valueOf(response.body()));
            default -> new Exception("Generic error");
        };

    }


}
