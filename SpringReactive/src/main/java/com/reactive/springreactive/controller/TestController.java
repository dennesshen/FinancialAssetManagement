package com.reactive.springreactive.controller;

import com.reactive.springreactive.feign.LongTaskApi;
import com.reactive.springreactive.feign.LongTaskApiByWebClient;
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
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

/**
 * @author christinehsieh on 2023/8/13
 */
@RestController
@RequestMapping("/finance/search")
@Slf4j
public class TestController {

    @Autowired
    private YahooFinanceService yahooFinanceService;

    @Autowired
    private SearchServiceCenter searchServiceCenter;

    @Autowired
    private LongTaskApi longTaskApi;

    @Autowired
    private LongTaskApiByWebClient longTaskApiByWebClient;


    @GetMapping("/demo1")
    public Mono<String> demo1(){
        return Mono.just("Hello WebFlux " + new Date() );
    }

    @GetMapping("/demo2")
    public Flux<String> demo2(){
        return Flux.just("Andy", "John", "Christine")
                .map(name -> "Hello " + name + " " + new Date() + "\n") ;

    }


    @GetMapping("/{symbol}")
    public Mono<String> searchSingle(@PathVariable String symbol) throws IOException {

        return yahooFinanceService.searchSingle(symbol)
                                  .map(s -> s.toString() + ", time : " + new Date());
    }


    @GetMapping(value = "/subscribe/{symbol}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> subscribeSingle(@PathVariable String symbol){

        return Flux.create(sink -> {
                               sink.next("start to polling : " + symbol);
                               searchServiceCenter.subscribeSingle(sink, symbol);
                               sink.onCancel(() -> {
                                                log.info("cancel");
                                                searchServiceCenter.unsubscribeSingle(sink, symbol);
                                            });
                          });
    }

    @GetMapping(value = "/subscribe2/{symbol}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> subscribeSingle2(@PathVariable String symbol){

        return searchServiceCenter.subscribeSingle2(symbol);

    }

//    @GetMapping("/{symbol}")
//    public Mono<String> test2(@PathVariable String symbol) throws IOException {
//
//        String traceId = UUID.randomUUID().toString();
//
//        System.out.println("main thread: " + Thread.currentThread().getName() + ", traceId: " + traceId);
//        Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1)).publish().autoConnect(1);
//
//        return longTaskApi.getSum()
//                   .map(s -> {
//                       System.out.println("map thread: " + Thread.currentThread().getName() + ", traceId: " + traceId);
//
//                       return s + ", time : " + new Date();
//                   })
//                .publishOn(Schedulers.newSingle("publish" + traceId))
//                .subscribeOn(Schedulers.newSingle(traceId))
//                .doFirst(() -> {
//                    System.out.println("doFirst thread: " + Thread.currentThread().getName() + ", traceId: " + traceId);
//                })
//                .doOnSubscribe(s -> {
//                    System.out.println("doOnSubscribe thread: " + Thread.currentThread().getName() + ", traceId: " + traceId);
//                })
//                .doOnNext(s -> {
//                    System.out.println("doOnNext thread: " + Thread.currentThread().getName() + ", traceId: " + traceId);
//                })
//                .doOnCancel(() -> {
//                    System.out.println("doOnCancel thread: " + Thread.currentThread().getName() + ", traceId: " + traceId);
//                });
//    }

    @GetMapping("/test")
    public Mono<String> test(){

        System.out.println("main thread: " + Thread.currentThread().getName());

        Mono<String> stringMono = Mono.defer(() -> longTaskApiByWebClient.sendApiMono())
                .doOnSubscribe(s -> {
                    System.out.println("doOnSubscribe thread: " + Thread.currentThread().getName());
                })
                .doFirst(() -> {
                    System.out.println("doFirst thread: " + Thread.currentThread().getName());
                })
                .subscribeOn(Schedulers.newSingle("subscribeOn"))
                .doOnNext(s -> {
                    System.out.println("doOnNext thread: " + Thread.currentThread().getName());
                });

        return stringMono;
    }
}











