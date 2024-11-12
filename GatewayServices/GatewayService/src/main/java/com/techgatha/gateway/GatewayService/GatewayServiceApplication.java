package com.techgatha.gateway.GatewayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinitionRouteLocator;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {


		SpringApplication.run(GatewayServiceApplication.class, args);
	}

}
