package com.webflux.springmvc.feign;

import com.webflux.springmvc.dto.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author christinehsieh on 2023/8/18
 */
@FeignClient(name = "yahoo-finance", url = "https://query1.finance.yahoo.com")
public interface YahooFinanceFiegnClient {

    @GetMapping(value = "/v8/finance/chart/{symbol}")
    Stock askSingle(@PathVariable String symbol);

}
