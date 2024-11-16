package com.cb.ResilienceSpringBootWebDemo;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class ResilienceSpringBootWebDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResilienceSpringBootWebDemoApplication.class, args);
	}

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
//		TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
//				.timeoutDuration(Duration.ofSeconds(4))
//				.build();
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
				.failureRateThreshold(50)
				.waitDurationInOpenState(Duration.ofMillis(1000))
				.slidingWindowSize(2)
				.build();

		return factory -> factory.configureDefault(id -> {
			System.out.println("Id "+id);
			return new Resilience4JConfigBuilder(id)
					//.timeLimiterConfig(timeLimiterConfig)
					.circuitBreakerConfig(circuitBreakerConfig)
					.build();
		});
	}

}
