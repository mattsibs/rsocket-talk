package com.blog.rsocket.talk.server;

import com.blog.rsocket.talk.client.NewsItem;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@Component
@Slf4j
@AllArgsConstructor
public class MonitoringService {

    private final EmitterProcessor<LostEvent> emitterProcessor = EmitterProcessor.create();

    public Flux<LostEvent> lostEventFlux() {
        return Flux.from(emitterProcessor);
    }

    public synchronized void  bufferOverflow(final NewsItem newsItem) {
        log.info("Buffer overflow {}", newsItem);
        emitterProcessor.onNext(new LostEvent(newsItem.getId()));
    }

    public Flux<Integer> stats() {
        return lostEventFlux().buffer(Duration.ofSeconds(1))
                .map(List::size);
    }

    @Data
    @AllArgsConstructor
    public static class LostEvent {
        private final UUID uuid;
    }
}
