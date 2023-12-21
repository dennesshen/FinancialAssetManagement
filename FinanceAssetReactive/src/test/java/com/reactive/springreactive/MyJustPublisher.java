package com.reactive.springreactive;

/**
 * @author christinehsieh on 2023/9/9
 */
public class MyJustPublisher<T>  extends MyPublisher<T> {

    private T[] publishData;

    public MyJustPublisher(T[] data) {
        this.publishData = data;
    }

    public void subscribe(MySubscriber<T> subcriber) {
        publish(subcriber);
    }


    protected void publish(MySubscriber subcriber) {
        for (T data : publishData) {
            subcriber.doNext(data);
        }
    }


}
