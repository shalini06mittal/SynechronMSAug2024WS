package com.forex.ForexExchangeService.repository;

import com.forex.ForexExchangeService.model.CurrencyExcchange;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRepository extends CrudRepository<CurrencyExcchange, Long>{

	public CurrencyExcchange findByFromAndTo(String from,String to);
}
