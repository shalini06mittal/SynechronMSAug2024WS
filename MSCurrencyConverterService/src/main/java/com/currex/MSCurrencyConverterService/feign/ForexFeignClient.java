package com.currex.MSCurrencyConverterService.feign;

import com.currex.MSCurrencyConverterService.model.ConversionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// declarative rest client
@FeignClient(name="MSForexExchangeService")
public interface ForexFeignClient {

    @GetMapping("/forex-exchange/from/{from}/to/{to}")
    public ConversionBean getConversionMultiple(
            @PathVariable String from, @PathVariable String to);

}

