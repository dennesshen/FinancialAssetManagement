package com.reactive.springreactive;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author christinehsieh on 2023/9/9
 */
public class PublisherSubcriberTest {


    @Test
    public void testPublisherSubcriber(){

        MyPublisher<String> publisher = new MyPublisher<>();
        MySubcriber<String> subcriber = new MySubcriber<>();
        publisher.just("Andy", "John", "Christine")
                 .map(name -> "Hello " + name + " " + new Date())
                 .subscribe(subcriber);

    }

}

class MyPublisher<T> {

    private MySubcriber<T> subcriber;

    private T[] publishData;
    private Function<T, T> mapper;

    public MyPublisher<T> just(T... data) {
        this.publishData = data;
        return this;
    }

    public MyPublisher<T> map(Function<T, T> mapper){
        this.mapper = mapper;
        return this;
    }
    public void subscribe(MySubcriber<T> subcriber) {
        this.subcriber = subcriber;
        publish();
    }

    private void publish() {
        for (T data : publishData) {

            if(mapper != null){
                data = mapper.apply(data);
            }

            subcriber.doNext(data);
        }
    }


}

@Slf4j
class MySubcriber<R> {

    private Consumer<R> howToDealGetData;

    public MySubcriber() {
        this.howToDealGetData = data -> System.out.println(data);
    }

    public void subscribe(Consumer<R> howToDealGetData) {
        this.howToDealGetData = howToDealGetData;
    }

    public void doNext(R data){
        howToDealGetData.accept(data);
    }


}


