package com.reactive.springreactive;

import java.util.function.Function;

/**
 * @author christinehsieh on 2023/9/9
 */
public class MapPublisher<T, V> extends MyPublisher<V>{

    private Function<T, V> function;
    private MyPublisher<T> source;


    public MapPublisher(MyPublisher<T> source, Function<T, V> function) {
        this.source = source;
        this.function = function;
    }

    @Override
    public void subscribe(MySubscriber<V> subcriber) {
        source.subscribe(data -> subcriber.doNext(function.apply(data)));
    }


}
