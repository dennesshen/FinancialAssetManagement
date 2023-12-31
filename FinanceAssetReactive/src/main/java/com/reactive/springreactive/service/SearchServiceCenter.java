package com.reactive.springreactive.service;

import com.reactive.springreactive.dto.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author christinehsieh on 2023/8/22
 */
@Service
@Slf4j
public class SearchServiceCenter {

    private Map<String, Flux> fluxMap = new ConcurrentHashMap<>();

    @Autowired
    private YahooFinanceService yahooFinanceService;

    public Flux<Stock> subscribeSingle(String symbol){

        Flux targetFlux = fluxMap.get(symbol);
        if (targetFlux == null){
            targetFlux = getStockPublisher(symbol);
            fluxMap.put(symbol, targetFlux);
        }

        return targetFlux;

    }

    private Flux<Stock> getStockPublisher(String symbol){

        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick -> yahooFinanceService.searchSingle(symbol))
                .doOnCancel(() -> {
                    log.info("Searching price for {} is cancelled ", symbol);
                    fluxMap.remove(symbol);
                })
                .publish()
                .refCount(1);

    }



}



