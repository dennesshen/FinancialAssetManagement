package com.reactive.springreactive;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.function.Function;

/**
 * @author christinehsieh on 2023/9/9
 */
public class Test2 {

    @Test
    public void testPublisherSubcriber(){

        MySubcriber2 subcriber = new MySubcriber2() {
            @Override
            public <R> void doNext(R data) {
                System.out.println(data);
            }
        };
        MyPublisher2.just("Andy", "John", "Christine")
                 .subscribe(subcriber);

    }
}


class MyPublisher2<T> {

    private T[] publishData;

    public MyPublisher2(T[] data) {
        this.publishData = data;
    }

    public static <T> MyPublisher2<T> just(T... data) {
        return new MyPublisher2<>(data);
    }

    public void subscribe(MySubcriber2 subcriber) {
        publish(subcriber);
    }


    private void publish(MySubcriber2 subcriber) {
        for (T data : publishData) {
            subcriber.doNext(data);
        }
    }

}


@FunctionalInterface
interface MySubcriber2 {

    <R> void doNext(R data);


}
