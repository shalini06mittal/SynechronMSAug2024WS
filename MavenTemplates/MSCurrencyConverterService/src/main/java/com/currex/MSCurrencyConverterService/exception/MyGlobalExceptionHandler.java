package com.currex.MSCurrencyConverterService.exception;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class MyGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleMyFeignClientException(FeignException exception) {
        System.out.println("global handler");
        Map<String, String> map = Map.of("error", "wrong");
        return ResponseEntity.of(Optional.of(map));

    }

}
