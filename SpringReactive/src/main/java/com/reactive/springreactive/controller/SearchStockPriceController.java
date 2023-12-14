package com.reactive.springreactive.controller;

import com.reactive.springreactive.dto.Stock;
import com.reactive.springreactive.service.SearchServiceCenter;
import com.reactive.springreactive.service.YahooFinanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author christinehsieh on 2023/8/13
 */
@RestController
@RequestMapping("/finance/search")
@Slf4j
public class SearchStockPriceController {

    @Autowired
    private YahooFinanceService yahooFinanceService;

    @Autowired
    private SearchServiceCenter searchServiceCenter;

    @GetMapping("/{symbol}")
    public Mono<Stock> searchSingle(@PathVariable String symbol){

        return yahooFinanceService.searchSingle(symbol);

    }

    @GetMapping(value = "/subscribe/{symbol}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> subscribeSingle(@PathVariable String symbol){

        return searchServiceCenter.subscribeSingle(symbol);

    }

}











