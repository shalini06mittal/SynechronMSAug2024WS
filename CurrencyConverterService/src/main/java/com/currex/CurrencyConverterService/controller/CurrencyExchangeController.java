package com.currex.CurrencyConverterService.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.currex.CurrencyConverterService.model.ConversionBean;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyExchangeController {
	/**
	 * 1. hard coded rest template obj creation => @Bean
	 * 2. uri is hardcoded => dynamic => cloud
	 * 3. configuration of services at diff places
	 * 4. balancing load across instances => client side, server side
	 * 5. locating a service => service to be discoverable
	 * 	Discovery server => Eureka
	 *
	 *
	 * @param from
	 * @param to
	 * @param quantity
	 * @return
	 */
	@RequestMapping("/exchange/from/{from}/to/{to}/quantity/{quantity}")
	public ConversionBean calculateAmount(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {

		RestTemplate template = new RestTemplate();

		String url = "http://localhost:8000/forex-exchange/from/{from}/to/{to}";
		Map<String, String> map = new HashMap<>();
		map.put("from", from);
		map.put("to", to);
		//ConversionBean bean = template.getForObject(url, ConversionBean.class,map);
		ResponseEntity<ConversionBean> responseEntity = template.exchange(url, HttpMethod.GET,null, ConversionBean.class, map);
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getHeaders());
		ConversionBean bean = responseEntity.getBody();
		bean.setQuantity(quantity);
		bean.setTotalCalculatedAmount( bean.getConversionMultiple().multiply(quantity));
		return responseEntity.getBody();
	}

	@PostMapping
	public  String post(){
		RestTemplate template = new RestTemplate();
		return "";
	}
	
}
