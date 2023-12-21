package com.reactive.springreactive.service;

import com.reactive.springreactive.dto.Stock;
import com.reactive.springreactive.feign.YahooFinanceFiegnClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @author christinehsieh on 2023/8/18
 */
@Service
public class YahooFinanceService {

    @Autowired
    private YahooFinanceFiegnClient yahooFinance;

    public Mono<Stock> searchSingle(String symbol){

        return yahooFinance.askSingle(symbol);
    }
}
