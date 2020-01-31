package com.blog.rsocket.talk.server.controller;

import com.blog.rsocket.talk.server.events.EventService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class MessageController {

    private final EventService eventService;

    @MessageMapping("get.messages")
    public Flux<Object> subscribe() {
        return eventService.subscribe();
    }

    @MessageMapping("send.message")
    public Mono<Void> publish(final String message) {
        eventService.message();
        return Mono.empty();
    }
}