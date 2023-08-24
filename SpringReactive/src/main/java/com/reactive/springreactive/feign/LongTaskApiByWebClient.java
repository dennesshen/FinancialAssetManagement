package com.reactive.springreactive.feign;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @author christinehsieh on 2023/8/21
 */
@Service
public class LongTaskApiByWebClient{


    public Mono<String> sendApiMono(){
        return Mono.fromFuture(CompletableFuture.supplyAsync(() -> sendApi(),
                               Executors.newSingleThreadExecutor()))
                .publishOn(Schedulers.boundedElastic());
    }

    public String sendApi() {

        System.out.println("LongTaskApiByWebClient thread: " + Thread.currentThread().getName());
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create("http://localhost:8082/longtime"))
                                         .GET()
                                         .build();
        StringBuilder result = new StringBuilder();
        try {
            HttpResponse<String> response =
            client.send(request, HttpResponse.BodyHandlers.ofString());
            result.append(response.body());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result.toString();
    }


}
