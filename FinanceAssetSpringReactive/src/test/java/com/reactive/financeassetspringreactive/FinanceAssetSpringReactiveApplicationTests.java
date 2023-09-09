package com.reactive.financeassetspringreactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.function.Consumer;

@SpringBootTest
class FinanceAssetSpringReactiveApplicationTests {



    @Test
    void contextLoads() {


        MyPublisher.just("Andy", "John", "Christine")
                   .subcribe(s -> System.out.println("Hello " + s),
                             s -> System.out.println("Hello2 " + s + " " +new Date()));


    }

}
