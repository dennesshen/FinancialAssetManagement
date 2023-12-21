package com.reactive.springreactive;

import org.reactivestreams.Publisher;

import java.util.function.Function;

/**
 * @author christinehsieh on 2023/9/9
 */
public abstract class MyPublisher<T>{

    public static <T> MyJustPublisher<T> just(T... data) {
        return new MyJustPublisher<>(data);
    }

    public <R> MyPublisher<R> map(Function<T, R> function) {
        return new MapPublisher<>(this, function);
    }


    public abstract void subscribe(MySubscriber<T> subcriber);


}
