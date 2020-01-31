package com.blog.rsocket.talk.server.events;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ReplayProcessor;
import reactor.util.function.Tuple2;

@AllArgsConstructor
@Component
public class EventService {

  private final EmitterProcessor<Object> emitterProcessor = EmitterProcessor.create(10);

  @PostConstruct
  public void init() {
    for (int i = 0; i < 10; i++) {
      this.emitterProcessor.onNext(i);
    }
  }

  public void message() {
    this.emitterProcessor.onNext("Ping");
  }

  public Flux<Object> subscribe() {
    return Flux.zip(Flux.interval(Duration.of(1, ChronoUnit.SECONDS)), Flux.from(emitterProcessor))
            .map(Tuple2::getT2);
  }
}
