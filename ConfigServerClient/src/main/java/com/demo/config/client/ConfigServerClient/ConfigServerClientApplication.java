package com.demo.config.client.ConfigServerClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})

public class ConfigServerClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerClientApplication.class, args);
	}

}
