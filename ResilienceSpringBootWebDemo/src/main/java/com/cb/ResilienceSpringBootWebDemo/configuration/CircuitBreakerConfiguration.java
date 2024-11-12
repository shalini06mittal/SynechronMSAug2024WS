package com.cb.ResilienceSpringBootWebDemo.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class CircuitBreakerConfiguration
{

    Logger logger = Logger.getLogger(CircuitBreakerConfiguration.class.getName());
    @Bean
    public RegistryEventConsumer<CircuitBreaker> myRegistryEventCircuitBreakerConsumer(){
        return new RegistryEventConsumer<CircuitBreaker>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
                logger.info(String.format("Enter added %s ",entryAddedEvent.getAddedEntry().getName()));
                entryAddedEvent.getAddedEntry().getEventPublisher()
                        .onEvent(event -> {
                            logger.info(String.format("Circuit on %s with event type %s", event.getCircuitBreakerName(),event.getEventType()));
                        });
                entryAddedEvent.getAddedEntry().getEventPublisher().onStateTransition(event -> {
                    logger.info(String.format("CB %s with event type %s transition from %s to %s",
                            event.getCircuitBreakerName(), event.getEventType(), event.getStateTransition().getFromState(),
                            event.getStateTransition().getToState()
                            ));
                });
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {
                logger.info(String.format("Entry removed %s ",entryRemoveEvent.getRemovedEntry().getName()));

            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {
               logger.info(String.format("Entry replaced  form %s to %s ",entryReplacedEvent.getOldEntry().getName(), entryReplacedEvent.getNewEntry().getName()));
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
                        logger.info(String.format("retry %s for %s number of attempts %s ",
                                event.getName(), event.getEventType(), event.getNumberOfRetryAttempts())

                        ));
                entryAddedEvent.getAddedEntry().getEventPublisher().onEvent(event ->
                        logger.info(String.format("retry event %s on success of %s and %s",
                                event.getName(), event.getEventType(),
                                event.getNumberOfRetryAttempts())

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
    public RegistryEventConsumer<TimeLimiter> myRegistryEventTimerlimitConsumer(){
        System.out.println("time limiter");
        return new RegistryEventConsumer<TimeLimiter>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<TimeLimiter> entryAddedEvent) {
                entryAddedEvent.getAddedEntry().getEventPublisher().onEvent(event->
                        logger.info(String.format("Time Limiter Type %s ",
                                event.getEventType(), event.getTimeLimiterName()))
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
    public RegistryEventConsumer<RateLimiter> myRegistryEventRatelimitConsumer() {
        System.out.println("rate limiter");
        return new RegistryEventConsumer<RateLimiter>() {

            @Override
            public void onEntryAddedEvent(EntryAddedEvent<RateLimiter> entryAddedEvent) {
                entryAddedEvent.getAddedEntry().getEventPublisher().onEvent(event->
                        logger.info(String.format("Rate Limiter Type %s Name %s Permits %s ",
                                event.getEventType(), event.getRateLimiterName(),
                                event.getNumberOfPermits())
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
}
