package com.cb.ResilienceSpringBootReactorDemo.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import io.github.resilience4j.core.functions.Either;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
class CircuitBreakerConfiguration {

    @Bean
    public RegistryEventConsumer<CircuitBreaker> myRegistryEventConsumer() {
        System.out.println("event");
        return new RegistryEventConsumer<CircuitBreaker>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {

                entryAddedEvent.getAddedEntry().getEventPublisher().onEvent(event ->
                        log.warn("circuit breaker {} on success of {} ",
                                event.getCircuitBreakerName(), event.getEventType()

                        ));
                entryAddedEvent.getAddedEntry().getEventPublisher().onStateTransition(event->
                        log.warn("circuit breaker {} on  {} from {} to {} ",
                                event.getCircuitBreakerName(), event.getEventType(),
                                event.getStateTransition().getFromState(),
                                event.getStateTransition().getToState()

                        ));
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {

            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {

            }
        };
    }
//    @Bean
//    CircuitBreaker exampleCircuitBreaker(CircuitBreakerRegistry registry) {
//        System.out.println("cb configured");
//        CircuitBreaker c = registry.circuitBreaker("example");
//        System.out.println(c);
//        return  c;
//    }

  //  @Bean
    public CircuitBreakerConfigCustomizer testCustomizer() {
        System.out.println("customer cb");
        return CircuitBreakerConfigCustomizer
                .of("example", builder -> builder.slidingWindowSize(3));
    }

//    @Bean
//    Retry remittanceServiceRetry(RetryRegistry registry) {
//        return registry.retry("remittance-service");
//    }
//
//    @Bean
//    TimeLimiter remittanceServiceTimeLimiter(TimeLimiterRegistry registry) {
//        return registry.timeLimiter("remittance-service");
//    }
}
