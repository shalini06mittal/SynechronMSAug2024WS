package com.currex.MSCurrencyConverterService.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.currex.MSCurrencyConverterService.feign.ForexFeignClient;
import com.currex.MSCurrencyConverterService.model.ConversionBean;
import io.micrometer.observation.annotation.Observed;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private RestTemplate template;
	@RequestMapping("/exchange/from/{from}/to/{to}/quantity/{quantity}")
	@Observed(name = "user.name", contextualName = "CCS --> FS",
			lowCardinalityKeyValues = {"userType","userType2"})
	public ConversionBean calculateAmount1(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity)
	{
		String url = "http://MSForexExchangeService/forex-exchange/from/{from}/to/{to}";
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

	@Autowired
	private ForexFeignClient forexFeignClient;
	@RequestMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
	@Observed(name = "user.name", contextualName = "CCS --> FS",
			lowCardinalityKeyValues = {"userType","userType2"})
	public ConversionBean calculateAmount(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity)
	{
		ConversionBean bean = forexFeignClient.getConversionMultiple(from,to);
		bean.setQuantity(quantity);
		bean.setTotalCalculatedAmount( bean.getConversionMultiple().multiply(quantity));
		return bean;
	}
}
