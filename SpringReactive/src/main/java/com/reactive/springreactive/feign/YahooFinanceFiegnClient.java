package com.reactive.springreactive.feign;

import com.reactive.springreactive.dto.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

/**
 * @author christinehsieh on 2023/8/18
 */
@ReactiveFeignClient(name = "yahoo-finance", url = "https://query1.finance.yahoo.com")
public interface YahooFinanceFiegnClient {

    @GetMapping(value = "/v8/finance/chart/{symbol}")
    Mono<Stock> askSingle(@PathVariable String symbol);

}
