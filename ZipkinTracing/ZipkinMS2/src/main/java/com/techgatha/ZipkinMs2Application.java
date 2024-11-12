package com.techgatha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ZipkinMs2Application {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinMs2Application.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}

@RestController
class Microservice2Controller {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RestTemplate restTemplate;

	

//	@Bean
//	public Sampler defaultSampler() {
//		return Sampler.ALWAYS_SAMPLE;
//	}

	@GetMapping(value = "/ms2")
	public String method2() {
		//LOG.info("Inside method2");
		//String baseUrl = "http://localhost:8083/microservice3";
		try {
			Thread.sleep(1000);
		} catch (Exception ex) {

		}
		//String response = (String) restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class).getBody();
		//LOG.info("The response received by method2 is " + response);
		return "from ms";
	}
}