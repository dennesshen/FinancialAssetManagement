package com.reactive.springreactive;

import org.junit.jupiter.api.Test;

/**
 * @author christinehsieh on 2023/9/9
 */
public class PulisherSubscriberTest {


    @Test
    public void testPublisherSubcriber(){


        MyPublisher.just("1", "2", "3")
                .map(data -> Integer.valueOf(data) + 1)
                .subscribe(data -> System.out.println(data));


    }


}
