package com.reactive.springreactive;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
class SpringReactiveApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {


        Mono<String> mono =
//        Mono.fromSupplier(() -> getData());
        Mono.fromCallable(() -> getData());
//        Mono.fromFuture(CompletableFuture.supplyAsync(() -> getData(),
//                                                      Executors.newSingleThreadExecutor()));

        System.out.println("main thread: " + Thread.currentThread().getName());
        mono.map(s -> s + " -Dan")
            .doOnSubscribe(s -> System.out.println("doOnSubscribe: " + Thread.currentThread().getName()))
            .doOnNext(s -> System.out.println("doOnNext: " + s + " " + Thread.currentThread().getName()))
            .doOnSuccess(s -> System.out.println("doOnSuccess: " + s + " " + Thread.currentThread().getName()))
            .doFirst(() -> System.out.println("doFirst: " + Thread.currentThread().getName()))
            .doOnCancel(() -> System.out.println("doOnCancel: " + Thread.currentThread().getName()))
            .publishOn(Schedulers.newSingle("publishOnThread"))
            .subscribeOn(Schedulers.newSingle("subscribeOnThread"))
            .subscribe(s -> System.out.println("subscribe : " + s + " " + Thread.currentThread().getName()));

        System.out.println("main thread: " + Thread.currentThread().getName());
        Thread.sleep(10000);

    }
    private String getData(){
        System.out.println("getData: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "hello world";
    }

}
