package com.reactive.financeassetspringreactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author christinehsieh on 2023/9/9
 */
public class WebFluxTest {

    @Test
    public void testMono(){

        Mono.just("Andy")
            .doOnNext(s -> System.out.println("doNext1 : " + s))

            .map(name -> {
                System.out.println("start to transform" );
                return "Hello " + name;
            })
            .doOnNext(s -> System.out.println("doNext2 : " + s))
            .doOnSubscribe(subscription -> System.out.println("doOnSubscribe"))
            .subscribe(s -> System.out.println("subscriber do : " + s));


        System.out.println("test complete");
    }


    @Test
    public void testFlux(){

        Flux.just("Andy", "John", "Christine")
                .doOnNext(s -> System.out.println("doNext1 : " + s))

                .map(name -> {
                    System.out.println("start to transform" );
                    return "Hello " + name;
                })
                .doOnNext(s -> System.out.println("doNext2 : " + s))
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe"))
                .subscribe(s -> System.out.println("subscriber do : " + s));


        System.out.println("test complete");
    }

}
