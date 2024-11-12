package com.forex.MSForexExchangeService.controller;

import com.forex.MSForexExchangeService.model.CurrencyExcchange;
import com.forex.MSForexExchangeService.repository.ExchangeRepository;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexController {

	@Autowired
    ExchangeRepository repository;

    @Autowired
    Environment environment;
    @GetMapping("/forex-exchange/from/{from}/to/{to}")
    @Observed(name = "user.name", contextualName = "FS --> CCS",
            lowCardinalityKeyValues = {"userType","userType2"})
    public CurrencyExcchange retrieveExchangeValue
            (@PathVariable String from, @PathVariable String to){
    	System.out.println(repository.count());
    	CurrencyExcchange exchangeValue =
                repository.findByFromAndTo(from, to);
        System.out.println(environment.getProperty("java.version"));
        exchangeValue.setPort(environment.getProperty("server.port", Integer.class));
        System.out.println(exchangeValue.getPort());
        return exchangeValue;
    }

}

