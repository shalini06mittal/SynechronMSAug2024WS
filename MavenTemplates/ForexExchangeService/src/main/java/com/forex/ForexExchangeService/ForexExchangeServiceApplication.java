package com.forex.ForexExchangeService;

import com.forex.ForexExchangeService.model.CurrencyExcchange;
import com.forex.ForexExchangeService.repository.ExchangeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class ForexExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForexExchangeServiceApplication.class, args);
	}

	@Autowired
	private ExchangeRepository repository;

	@PostConstruct
	public void initalize(){
		CurrencyExcchange ob1 = new CurrencyExcchange(1L, "USA","IND", new BigDecimal(80));
		CurrencyExcchange ob2 = new CurrencyExcchange(2L, "USA","SGD", new BigDecimal(60));
		CurrencyExcchange ob3 = new CurrencyExcchange(3L, "UK","IND", new BigDecimal(90));
		repository.saveAll(Arrays.asList(ob1, ob2, ob3));
	}
}
