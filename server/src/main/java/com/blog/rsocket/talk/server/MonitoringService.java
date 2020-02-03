package com.blog.rsocket.talk.server;

import com.blog.rsocket.talk.server.controller.MonitoringController;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class MonitoringService {

    @Getter
    private final AtomicLong flushSpeed = new AtomicLong(1);
    private final EmitterProcessor<MonitoringController.AppStatus> emitterProcessor = EmitterProcessor.create();
    public final Collection<Object> newsBuffer = new ConcurrentLinkedQueue<>();

    public Flux<MonitoringController.AppStatus> monitorStatus() {
        return Flux.from(emitterProcessor);
    }

    public synchronized void monitorBuffer(final Object object) {

    }
}
