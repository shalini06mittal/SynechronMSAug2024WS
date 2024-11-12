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
	 * 1. Inject RestTemplate => 1 instance Autowried
	 * 2. Create URI
	 * 3. localhost:8000 => cloud [ url is hardcoded ] dynamic	url
	 * 4. Allow to uniquely identify a service a make a call to the service
	 * 5. service id :
	 * 6. services should be made discoverable
	 * 7. creating the URI for the placeholders
	 *
	 * Discovery Severs :
	 * 1. client side discovery : Eureka
	 * 2. server side discovery : Consul, AWS
	 *
	 */
	@GetMapping("/exchange/from/{from}/to/{to}/quantity/{quantity}")
	public ConversionBean calculateAmount(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity)
	{
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:8000/forex-exchange/from/{from}/to/{to}";
		Map<String, String> map = new HashMap<>();
		map.put("from", from);
		map.put("to", to);
		//ConversionBean bean1 = template.getForObject(url, ConversionBean.class,map);
		ResponseEntity<ConversionBean> responseEntity =
				template.exchange(url, HttpMethod.GET,null, ConversionBean.class, map);
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
