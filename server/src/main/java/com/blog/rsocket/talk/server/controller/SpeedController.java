package com.blog.rsocket.talk.server.controller;

import com.blog.rsocket.talk.server.events.NewsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class SpeedController {

    private final NewsService newsService;

    @MessageMapping("monitor.news.speed")
    public Mono<Void> setNewsSpeed(@RequestBody SpeedRequest speedRequest) {
        this.newsService.updateNewsRate(speedRequest.getSpeed());
        return Mono.empty();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpeedRequest {
        private Long speed;
    }

}
