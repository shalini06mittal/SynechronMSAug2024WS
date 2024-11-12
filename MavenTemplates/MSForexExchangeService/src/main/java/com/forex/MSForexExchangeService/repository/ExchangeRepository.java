package com.forex.MSForexExchangeService.repository;

import com.forex.MSForexExchangeService.model.CurrencyExcchange;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRepository extends CrudRepository<CurrencyExcchange, Long>{

	public CurrencyExcchange findByFromAndTo(String from,String to);
}
