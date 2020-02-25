package com.blog.rsocket.talk.server.events;

import com.blog.rsocket.talk.client.NewsItem;
import com.blog.rsocket.talk.server.MonitoringService;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class NewsService {

  private final EmitterProcessor<NewsItem> emitterProcessor;
  private final MonitoringService monitoringService;
  private final AtomicLong newsSpeed = new AtomicLong(10);

  public NewsService(final MonitoringService monitoringService) {
    this.monitoringService = monitoringService;
    this.emitterProcessor = EmitterProcessor.create(10);
  }

  @Scheduled(fixedRate = 1000)
  public void reportCurrentTime() {
    long flushSpeed = this.newsSpeed.get();
    if (flushSpeed == 0) {
      return;
    }
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
  }

  public Flux<NewsItem> newsFluxWithBackPressure() {
    return Flux.from(emitterProcessor)
        .onBackpressureBuffer(
            10, monitoringService::bufferOverflow, BufferOverflowStrategy.DROP_OLDEST);
  }

  public Flux<NewsItem> rawNews() {
    return Flux.from(emitterProcessor);
  }

  public void updateNewsRate(final Long rate) {
    newsSpeed.set(rate);
  }
}
