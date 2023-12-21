package com.reactive.financeassetspringreactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author christinehsieh on 2023/9/9
 */
@RestController
@RequestMapping("/demo1")
public class Demo1Controller {


    @GetMapping("/hello/mono")
    public Mono<String> helloMono(){

        return Mono.just("Hello mono " + new Date());

    }

    @GetMapping("/hello/flux")
    public Flux<String> helloFlux(){

        List<String> list = new ArrayList<>();
        list.add("Hello flux1 " + new Date() + "<br>" );
        list.add("Hello flux2 " + new Date() + "<br>" );
        list.add("Hello flux3 " + new Date() + "<br>" );

        return Flux.fromIterable(list);

    }


}
