package com.reactive.springreactive;

import org.bouncycastle.cert.ocsp.Req;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author christinehsieh on 2023/9/9
 */
public class WebFluxTest {

    @Test
    public void monoTest(){

        Mono.just("Andy")
            .map(name -> "Hello " + name)
            .subscribe(System.out::println);


    }

    @Test
    public void monoTest2(){

        Mono.just("Andy")
            .doOnNext(s -> System.out.println("doOnNext1 :" + s))
            .map(name -> "Hello " + name)
            .doOnNext(s -> System.out.println("doOnNext2 :" + s))
            .doOnSubscribe(subscription -> System.out.println("doOnSubscribe"))
            .subscribe(s -> System.out.println("subscribe :" + s));
    }

    @Test
    public void fluxTest(){

        Flux.just("Andy", "John", "Christine")
            .doOnNext(s -> System.out.println("doOnNext1 :" + s))
            .map(name -> "Hello " + name)
            .doOnNext(s -> System.out.println("doOnNext2 :" + s))
            .doOnSubscribe(subscription -> System.out.println("doOnSubscribe"))
            .subscribe(s -> System.out.println("subscribe :" + s));
    }


    @Test
    public void simulateWebflux(){

        Request request = new Request("10.1.0.7", "2330.TW");


        Mono.just(getPrice(request.body()))
            .map(price -> request.body() + " price is " + price)
            .subscribe(s -> System.out.println("將結果：{ " + s + " }發向這個url的地址 " +request.sourceUrl()));
        



    }

    private Integer getPrice(String symbol) {

        Map<String, Integer> stockPrice = Map.of("2330.TW", 600,
                                                 "2317.TW", 1000,
                                                 "3008.TW", 500);

        return stockPrice.get(symbol);


    }


}


record Request(String sourceUrl, String body){

}


