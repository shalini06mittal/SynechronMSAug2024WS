package com.cb.ResilienceSpringBootWebDemo.controller;
import com.cb.ResilienceSpringBootWebDemo.api.ExternalAPICaller;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

//ms1->ms2->ms3
@RestController
@RequestMapping("/api")
//@CircuitBreaker(name = "example",fallbackMethod = "fallbackAfterCircuitBreaker")
public class ApiController {

    @Autowired
    private ExternalAPICaller externalAPICaller;

    private static final String circuitbreaker = "example";

    @GetMapping("/cb")
    public String callExternlApi(){
        return externalAPICaller.callApi();
    }
    @GetMapping("/cb/{id}")
    @CircuitBreaker(name = circuitbreaker,fallbackMethod = "fallbackAfterCircuitBreaker")
    public String callExternalApi(@PathVariable int id){
        return externalAPICaller.callApiThrowsException(id);
    }

    @GetMapping("/circuit-breaker/{delay}")
    @CircuitBreaker(name = circuitbreaker)
    public String circuitBreakerApiSLowCallRate(@PathVariable long delay) {
        return externalAPICaller.callApiSlowRate(delay);
    }

    @GetMapping("/retry/{message}")
    @Retry(name = "retryApi", fallbackMethod = "fallbackAfterRetry")
    public String retryApi(@PathVariable String message) {
        System.out.println("retry api");
        return externalAPICaller.callApiRetry(message);
    }

    @GetMapping("/time-limiter/{delay}")
   // @CircuitBreaker(name = circuitBreaker, fallbackMethod = "fallbackAfterTimeLimiter")
    @TimeLimiter(name = "timeLimiterApi", fallbackMethod ="fallbackAfterTimeLimiter" )
    public CompletableFuture<String> timeLimiterApi(@PathVariable long delay) {
        System.out.println("time limiter endpoint");
        return CompletableFuture.supplyAsync(()->externalAPICaller.callApiWithDelay(delay));
    }

    @GetMapping("/rate-limiter")
    @RateLimiter(name = "rateLimiterApi")
    public String rateLimitApi() {

        return externalAPICaller.callApi();
    }
    public CompletableFuture<String> fallbackAfterTimeLimiter(Exception ex) {
        System.out.println(ex.getMessage());
        return CompletableFuture.supplyAsync(()->"fallback");
    }
    public String fallbackAfterRetry(Exception ex) {
        return "all retries have exhausted";
    }
    public String fallbackAfterCircuitBreaker(Exception e){
        System.out.println(e.getMessage());
        return "from fallback response";
    }

}
