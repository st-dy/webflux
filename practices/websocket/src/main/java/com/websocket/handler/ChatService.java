package com.websocket.handler;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * packageName    : com.websocket.handler
 * fileName       : ChatService
 * author         : okdori
 * date           : 12/11/23
 * description    :
 */

@Service
public class ChatService {
    private static Map<String, Sinks.Many<Chat>> chatSinkMap = new ConcurrentHashMap<>();

    public Flux<Chat> register(String iam) {
        Sinks.Many<Chat> sink = Sinks.many().unicast().onBackpressureBuffer();

        chatSinkMap.put(iam, sink);
        return sink.asFlux();
    }

    public boolean sendChat(String iam, Chat chat) {
        Sinks.Many<Chat> sink = chatSinkMap.get(iam);
        if (sink == null) return false;
        sink.tryEmitNext(chat);

        return true;
    }
}
