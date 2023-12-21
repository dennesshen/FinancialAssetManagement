package com.reactive.springreactive.controller;

import com.reactive.springreactive.service.SearchServiceCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author christinehsieh on 2023/12/19
 */
@Controller
@Slf4j
public class StockSocketController {
    private final Map<UUID, Map<String, Disposable>> subscibeMap = new HashMap<>();

    @Autowired
    private SearchServiceCenter searchServiceCenter;

    @MessageMapping("stock")
    public Flux<Object> channel(Flux<String> symbol){

        UUID uuid = UUID.randomUUID();

        return symbol
                .doOnNext(name -> log.info("request: " + name))
                .flatMap(name -> {
                    Map<String, Disposable> map = subscibeMap.get(uuid);
                    if (map != null && map.containsKey(name)) {
                        map.remove(name).dispose();
                        return Flux.empty();
                    }

                    return Flux.create(sink -> {
                        Disposable subscribe = searchServiceCenter.subscribeSingle(name)
                                                                  .subscribe(result -> sink.next(result));
                        subscibeMap.compute(uuid, (key, value) -> {
                            if (value == null){
                                value = new HashMap<>();
                            }
                            value.put(name, subscribe);
                            return value;
                        });
                    });
                })
                .doOnCancel(() -> {
                    log.info("{} is cancel the channel", uuid);
                    subscibeMap.remove(uuid).forEach((k, v) -> v.dispose());
                });
    }



}
