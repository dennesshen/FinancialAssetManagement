package com.reactive.financeassetspringreactive;

import java.util.function.Consumer;

/**
 * @author christinehsieh on 2023/9/9
 */
@FunctionalInterface
public interface MySubscriber<R> {

    void doNext(R data);
}
