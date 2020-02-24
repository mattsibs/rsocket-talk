package com.blog.rsocket.talk.server;

import com.blog.rsocket.talk.server.events.NewsService;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatsService {

  private final MonitoringService monitoringService;
  private final NewsService newsService;

  public Flux<Integer> stats() {
    return monitoringService
        .lostEventFlux()
        .doOnNext(lostEvent -> log.info("Lost event {}", lostEvent))
        .window(Duration.ofSeconds(1))
        .flatMap(Flux::collectList)
        .map(
            lostEvents ->
                lostEvents.stream().map(x -> 1).reduce(0, (integer, lostEvent) -> integer + 1))
        .doOnNext(x -> log.info("Stats stream event {}", x));
  }

  public Flux<Integer> newsItemsPublishedPerSecond() {
    return newsService
        .rawNews()
        .window(Duration.ofSeconds(1))
        .flatMap(Flux::collectList)
        .map(
            lostEvents ->
                lostEvents.stream()
                        .map(x -> 1)
                        .reduce(0, (integer, lostEvent) -> integer + 1));
  }
}
