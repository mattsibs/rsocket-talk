package com.blog.rsocket.talk.server.controller;


import com.blog.rsocket.talk.server.MonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class MonitoringController {

    private final MonitoringService monitoringService;

    @MessageMapping("monitor.news.buffer")
    public Flux<Integer> subscribe() {
        return monitoringService.stats();
    }
}
