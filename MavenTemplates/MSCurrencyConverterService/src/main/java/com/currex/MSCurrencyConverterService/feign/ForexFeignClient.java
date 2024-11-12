package com.currex.MSCurrencyConverterService.feign;


import com.currex.MSCurrencyConverterService.config.FeignConfig;
import com.currex.MSCurrencyConverterService.model.ConversionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// declarative rest client
@FeignClient(name="MSForexExchangeService")//, configuration = FeignConfig.class)
public interface ForexFeignClient {

    @GetMapping("/forex-exchange/from/{from}/to/{to}")
    public ConversionBean getConversionMultiple(
            @PathVariable String from, @PathVariable String to);

   // @GetMapping("/forex-exchange/from/{from}/to/{to}")
//    public ConversionBean getConversionMultiple1(
//            @PathVariable String from, @PathVariable String to);

}

