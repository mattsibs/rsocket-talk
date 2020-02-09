package com.blog.rsocket.talk.server;

import com.blog.rsocket.talk.client.NewsItem;
import java.time.Duration;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
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

  public synchronized void bufferOverflow(final NewsItem newsItem) {
    log.info("Buffer overflow {}", newsItem);
    emitterProcessor.onNext(new LostEvent(newsItem.getId()));
  }

  public Flux<Integer> stats() {
    return lostEventFlux()
        .window(Duration.ofSeconds(1))
        .flatMap(Flux::collectList)
        .map(
            lostEvents ->
                lostEvents.stream().map(x -> 1).reduce(0, (integer, lostEvent) -> integer + 1))
        .doOnNext(x -> log.info("Stats stream event {}", x));
  }

  @Data
  @AllArgsConstructor
  public static class LostEvent {
    private final UUID uuid;
  }
}
