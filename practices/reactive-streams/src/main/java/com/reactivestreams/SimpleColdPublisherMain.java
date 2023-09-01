package com.reactivestreams;

import lombok.SneakyThrows;

/**
 * packageName    : com.reactivestreams
 * fileName       : SimpleColdePublisherMain
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */
public class SimpleColdPublisherMain {
    @SneakyThrows
    public static void main(String[] args) {
        // create publisher
        var publisher = new SimpleColdPublisher();

        // create subscriber1
        var subscriber = new SimpleNamedSubscriber<Integer>("subscriber1");
        publisher.subscribe(subscriber);

        Thread.sleep(5000);

        // create subscriber2
        var subscriber2 = new SimpleNamedSubscriber<Integer>("subscriber2");
        publisher.subscribe(subscriber2);
    }
}
