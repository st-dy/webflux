package com.sse.controller;

import com.sse.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * packageName    : com.sse.controller
 * fileName       : NotificationController
 * author         : okdori
 * date           : 12/11/23
 * description    :
 */

@RequiredArgsConstructor
@RequestMapping("/api/notifications")
@RestController
public class NotificationController {
    private static AtomicInteger lastEventId = new AtomicInteger(1);
    private final NotificationService notificationService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> getNotifications() {
        return notificationService.getMessageFromSink()
                .map(message -> ServerSentEvent
                        .builder(message)
                        .event("notification")
                        .id(String.valueOf(lastEventId.getAndIncrement()))
                        .comment("this is notification")
                        .build()
                );
    }

    @PostMapping
    public Mono<String> addNotification(@RequestBody Event event) {
        String message = event.getType() + ": " + event.getMessage();
        notificationService.tryEmitNext(message);

        return Mono.just("ok");
    }
}
