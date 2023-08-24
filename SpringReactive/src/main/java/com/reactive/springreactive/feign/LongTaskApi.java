package com.reactive.springreactive.feign;

import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

/**
 * @author christinehsieh on 2023/8/13
 */
@ReactiveFeignClient(name = "longtimeapi", url = "http://localhost:8082")
public interface LongTaskApi {

    @GetMapping("/longtime")
    Mono<String> getSum();

}
