package com.rsocket.talk.middleware.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class SpeedController {

    private final NewsService newsService;

    @MessageMapping("news.speed.consumption")
    public Mono<Void> setNewsSpeed(@RequestBody SpeedRequest speedRequest) {
        newsService.consumptionRatePerSecond.set(speedRequest.getSpeed());
        return Mono.empty();
    }

    @MessageMapping("monitor.news.buffer")
    public Flux<Integer> subscribe() {
        return Flux.empty();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpeedRequest {
        private Long speed;
    }

}
