package com.forex.ForexExchangeService.controller;

import com.forex.ForexExchangeService.model.CurrencyExcchange;
import com.forex.ForexExchangeService.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexController {

	@Autowired
    ExchangeRepository repository;

    @GetMapping("/forex-exchange/from/{from}/to/{to}")
    public CurrencyExcchange retrieveExchangeValue
            (@PathVariable String from, @PathVariable String to){

    	System.out.println(repository.count());
    	CurrencyExcchange exchangeValue =
                repository.findByFromAndTo(from, to);

        return exchangeValue;
    }

}

