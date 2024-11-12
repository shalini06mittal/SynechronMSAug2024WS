package com.currex.MSCurrencyConverterService.exception;

import org.springframework.http.HttpStatus;

public class MyFeignClientException extends Exception {

    private HttpStatus httpStatus;
    private String responseBody;

    public MyFeignClientException(HttpStatus httpStatus, String responseBody) {
        this.httpStatus = httpStatus;
        this.responseBody = responseBody;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
