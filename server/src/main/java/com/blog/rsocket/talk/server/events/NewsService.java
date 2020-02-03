package com.blog.rsocket.talk.server.events;

import com.blog.rsocket.talk.server.MonitoringService;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class NewsService {

  private final MonitoringService monitoringService;
  private final EmitterProcessor<NewsItem> emitterProcessor;
  private final Flux<NewsItem> flux;

  public NewsService(final MonitoringService monitoringService) {
    this.emitterProcessor = EmitterProcessor.create(10);
    this.monitoringService = monitoringService;
    this.flux =
        Flux.from(emitterProcessor).onBackpressureBuffer(10, monitoringService::monitorBuffer);
  }

  @PostConstruct
  public void init() {
    flux.subscribe();
    Flux.interval(Duration.ofSeconds(2))
        .doOnNext(
            aLong -> {
              AtomicLong flushSpeed = monitoringService.getFlushSpeed();
              log.info("Generating {} news items", flushSpeed);
              for (long i = 0; i < flushSpeed.get(); i++) {
                emitterProcessor.onNext(NewsFactory.random());
              }
            })
        .subscribe();
  }

  public Flux<NewsItem> subscribe() {
    return flux;
  }
}
