package com.blog.rsocket.talk.server.events;

import com.blog.rsocket.talk.client.NewsItem;
import com.blog.rsocket.talk.server.MonitoringService;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class NewsService {

  private final EmitterProcessor<NewsItem> emitterProcessor;
  private final MonitoringService monitoringService;
  private final AtomicLong flushSpeed = new AtomicLong(5);

  public NewsService(final MonitoringService monitoringService) {
    this.monitoringService = monitoringService;
    this.emitterProcessor = EmitterProcessor.create(10);
  }

  @PostConstruct
  public void init() {
    Flux.interval(Duration.ofSeconds(1))
        .doOnComplete(() -> log.info("Completed"))
        .doOnNext(
            aLong -> {
              long flushSpeed = this.flushSpeed.get();
              long perMillis = 1000 / flushSpeed;

              log.info("Generating {} per second", flushSpeed);

              int x = 0;
              while (x < 1000) {
                emitterProcessor.onNext(NewsFactory.random());
                x += perMillis;

                try {
                  Thread.sleep(perMillis);
                } catch (InterruptedException e) {
                  throw new RuntimeException(e);
                }
              }
            })
        .log()
        .subscribe();
  }

  public Flux<NewsItem> subscribe() {
    return Flux.from(emitterProcessor)
        .onBackpressureBuffer(
            50, monitoringService::bufferOverflow, BufferOverflowStrategy.DROP_OLDEST);
  }

  public void updateNewsRate(final Long rate) {
    flushSpeed.set(rate);
  }
}
