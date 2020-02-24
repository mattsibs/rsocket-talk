package com.blog.rsocket.talk.server.controller;


import com.blog.rsocket.talk.server.StatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MonitoringController {

    private final StatsService statsService;

    @MessageMapping("monitor.news.buffer")
    public Flux<Integer> subscribe() {
        log.info("Stats subscription");
        return statsService.stats();
    }
    @MessageMapping("monitor.news.rate")
    public Flux<Integer> subscribeToNewsRate() {
        log.info("Stats subscription");
        return statsService.newsItemsPublishedPerSecond();
    }
}
