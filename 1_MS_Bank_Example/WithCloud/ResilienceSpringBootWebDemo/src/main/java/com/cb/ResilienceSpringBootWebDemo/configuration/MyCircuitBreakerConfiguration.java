package com.cb.ResilienceSpringBootWebDemo.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyCircuitBreakerConfiguration {


    @Bean
    public RegistryEventConsumer<RateLimiter> myRegistryEventRatelimitConsumer() {
        System.out.println("rate limiter");
        return new RegistryEventConsumer<RateLimiter>() {

            @Override
            public void onEntryAddedEvent(EntryAddedEvent<RateLimiter> entryAddedEvent) {
                entryAddedEvent.getAddedEntry().getEventPublisher().onEvent(event->
                        log.warn("Rate Limiter Type {} Name {} Permits {} ",
                                event.getEventType(), event.getRateLimiterName(),
                                event.getNumberOfPermits()
                        )
                );
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<RateLimiter> entryRemoveEvent) {

            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<RateLimiter> entryReplacedEvent) {

            }
        };
    }

    @Bean
    public RegistryEventConsumer<TimeLimiter> myRegistryEventTimerlimitConsumer(){
        System.out.println("time limiter");
        return new RegistryEventConsumer<TimeLimiter>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<TimeLimiter> entryAddedEvent) {
                entryAddedEvent.getAddedEntry().getEventPublisher().onEvent(event->
                        log.warn("Time Limiter Type {} ",
                                event.getEventType(), event.getTimeLimiterName())
                );
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<TimeLimiter> entryRemoveEvent) {

            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<TimeLimiter> entryReplacedEvent) {

            }
        };
    }
    @Bean
    public RegistryEventConsumer<Retry> myRegistryEventRetryConsumer(){

        System.out.println("retry");
        return new RegistryEventConsumer<Retry>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<Retry> entryAddedEvent) {
                entryAddedEvent.getAddedEntry().getEventPublisher().onRetry(event ->
                        log.warn("retry {} for {} number of attempts {} ",
                                event.getName(), event.getEventType(), event.getNumberOfRetryAttempts()

                        ));
                entryAddedEvent.getAddedEntry().getEventPublisher().onEvent(event ->
                        log.warn("retry event {} on success of {} and {}",
                                event.getName(), event.getEventType(),
                                event.getNumberOfRetryAttempts()

                        ));
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<Retry> entryRemoveEvent) {

            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<Retry> entryReplacedEvent) {

            }
        };
    }
    @Bean
    public RegistryEventConsumer<CircuitBreaker> myCircuitBreakerRegistryEventConsumer() {
        System.out.println("Circuit Breaker event occured");
        return new RegistryEventConsumer<CircuitBreaker>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
                    entryAddedEvent.getAddedEntry()
                            .getEventPublisher().onEvent(event -> {
                                log.info("Circuit Breaker {} event {} occured",
                                        event.getCircuitBreakerName(), event.getEventType()
                                        );
                            });
                entryAddedEvent.getAddedEntry()
                        .getEventPublisher().onStateTransition(event -> {
                            log.info("Circuit Breaker {} event {} from {} to {}",
                                    event.getCircuitBreakerName(), event.getEventType(),
                                    event.getStateTransition().getFromState(),
                                    event.getStateTransition().getToState()

                            );
                        });
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {

            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {

            }
        };
    } // <1>
}
