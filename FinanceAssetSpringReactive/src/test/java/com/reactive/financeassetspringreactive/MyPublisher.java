package com.reactive.financeassetspringreactive;

/**
 * @author christinehsieh on 2023/9/9
 */
public class MyPublisher<T> {

    private MySubscriber<T>[] subsrcibers;

    private T[] publishData;


    public static <T> MyPublisher just(T... data){

        return new MyPublisher(data);
    }

    public MyPublisher(T[] data) {
        this.publishData = data;
    }

    public void subcribe(MySubscriber<T>... subscribers) {
        this.subsrcibers = subscribers;
        this.publish();
    }

    private void publish() {

        for (T data : publishData){
            for (MySubscriber<T> subsrciber : subsrcibers) {
                subsrciber.doNext(data);
            }
        }
    }





}
