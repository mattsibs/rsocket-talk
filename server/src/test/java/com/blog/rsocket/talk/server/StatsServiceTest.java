package com.blog.rsocket.talk.server;

import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

@ExtendWith(MockitoExtension.class)
class StatsServiceTest {

  @Mock private MonitoringService monitoringService;

  @InjectMocks private StatsService statsService;

  @Test
  void oneEvent_aggregatesToOne() {
    MonitoringService.LostEvent event = new MonitoringService.LostEvent(UUID.randomUUID());
    when(monitoringService.lostEventFlux()).thenReturn(Flux.just(event));

    StepVerifier.create(statsService.stats()).thenRequest(100).expectNext(1).verifyComplete();
  }

  @Test
  void oneEventPer490Millis_aggregatesToTwo() {
    MonitoringService.LostEvent event = new MonitoringService.LostEvent(UUID.randomUUID());
    when(monitoringService.lostEventFlux())
        .thenReturn(
            Flux.interval(Duration.ofMillis(490))
                .zipWith(Flux.just(event, event, event, event))
                .map(Tuple2::getT2));

    StepVerifier.create(statsService.stats())
        .expectNext(2)
        .expectNext(2)
        .verifyComplete();
  }
}
