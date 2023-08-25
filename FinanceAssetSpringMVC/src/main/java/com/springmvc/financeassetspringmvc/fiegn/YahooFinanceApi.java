package com.springmvc.financeassetspringmvc.fiegn;

import com.springmvc.financeassetspringmvc.dto.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author christinehsieh on 2023/8/25
 */
@FeignClient(name = "yahoo-finance", url = "https://query1.finance.yahoo.com")
public interface YahooFinanceApi {

    @GetMapping(value = "/v8/finance/chart/{symbol}")
    Stock askSingle(@PathVariable String symbol);


}
