package com.reactive.springreactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author christinehsieh on 2023/8/22
 */
@Service
public class SearchServiceCenter {

    private final Map<String, List<FluxSink>> subscribeMap = new ConcurrentHashMap<>();

    private Map<String, Flux> fluxMap = new ConcurrentHashMap<>();

    @Autowired
    private YahooFinanceService yahooFinanceService;

    public Flux<Object> subscribeSingle2(String symbol){

        Flux targetFlux = fluxMap.get(symbol);
        if (targetFlux == null){
            targetFlux = getStockPublisher(symbol);
            fluxMap.put(symbol, targetFlux);
        }

        return targetFlux;

    }

    private Flux<String> getStockPublisher(String symbol){

        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick -> yahooFinanceService.searchSingle(symbol))
                .map(stock -> stock.getSymbol() + " : " + stock.getRecentPrice())
                .doOnCancel(() -> {
                    System.out.println("canceled");
                    fluxMap.remove(symbol);
                })
                .doOnComplete(() -> System.out.println("completed"))
                .doOnTerminate(() -> System.out.println("terminated"))
                .publish()
                .refCount(1);

    }

    public void subscribeSingle(FluxSink<Object> sink, String symbol) {

        yahooFinanceService.searchSingle(symbol)
                           .subscribeOn(Schedulers.newSingle("subscribeSingle"))
                           .subscribe(s -> sink.next(s));

        subscribeMap.compute(symbol, (k, v) -> {
           if (v == null) {
               v = new ArrayList<>();
           }
           v.add(sink);

           return v;
        });

    }

    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    public void polling(){
        subscribeMap.forEach((k, v) -> {
                                yahooFinanceService.searchSingle(k)
                                        .subscribeOn(Schedulers.newSingle("polling"))
                                        .subscribe(s -> v.forEach(sink -> sink.next(s)));
                            });

    }

    public void unsubscribeSingle(FluxSink<Object> sink, String symbol) {
        subscribeMap.compute(symbol, (k, v) -> {
            if (v == null) return null;

            v.remove(sink);
            if (v.size() == 0) {
                v = null;
            }

            return v;
        });

    }
}
