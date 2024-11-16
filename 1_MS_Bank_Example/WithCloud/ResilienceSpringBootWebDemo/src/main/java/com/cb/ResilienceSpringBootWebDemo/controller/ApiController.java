package com.cb.ResilienceSpringBootWebDemo.controller;

import com.cb.ResilienceSpringBootWebDemo.api.ExternalAPICaller;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ExternalAPICaller externalAPICaller;

    private static final String circuitBreaker = "example";

    @RequestMapping("/circuitBreaker")
    @CircuitBreaker(name = circuitBreaker)
    public String callApi() {
        return externalAPICaller.callApi();
    }

    @RequestMapping("/circuitBreaker/{id}")
    @CircuitBreaker(name = circuitBreaker, fallbackMethod = "circuitBreakerApi")
    public String circuitBreakerApi(@PathVariable int id) {
        return externalAPICaller.callApi(id);
    }
    @RequestMapping("/circuitBreaker/delay/{delay}")
    @CircuitBreaker(name = circuitBreaker, fallbackMethod = "circuitBreakerApi")
    public String circuitBreakerApiSlowCallRate(@PathVariable long delay) {
        return externalAPICaller.callApiSlowRate(delay);
    }
    @RequestMapping("/retry/{message}")
    @Retry(name = "retryApi", fallbackMethod = "retryApi")
    public String retryApi(@PathVariable String message) {
        return externalAPICaller.callApiRetry(message);
    }
    @RequestMapping("/bs")
    @Retry(name = "retryApi", fallbackMethod = "retryApi")
    public String retryApiBs() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> responseEntity =
        template.exchange("http://localhost:8081/books/B1", HttpMethod.GET,
                  null,String.class);
        return responseEntity.getBody();
    }

    @GetMapping("/timeLimiter/{delay}")
    @CircuitBreaker(name = circuitBreaker, fallbackMethod = "timeLimiterApi")
    @TimeLimiter(name = "timeLimiterApi")//, fallbackMethod = "timeLimiterApi")
    public CompletableFuture<String> timeLimiterApi(@PathVariable long delay) {
        System.out.println("Time Limiter API");
        return CompletableFuture.supplyAsync(() -> externalAPICaller.callApiWithDelay(delay));
    }

    @GetMapping("/rateLimiter")
    @RateLimiter(name = "rateLimiterApi")
    public String rateLimiterApi() {
        System.out.println("Rate Limiter API");
        return externalAPICaller.callApi();
    }
    public CompletableFuture<String> timeLimiterApi(Exception ex){
        return CompletableFuture.supplyAsync(() -> "Fallback called");
    }
    public String circuitBreakerApi(Exception ex){
        return ex.getMessage();
    }
    public String retryApi(Exception ex){
        return ex.getMessage();
    }

}

