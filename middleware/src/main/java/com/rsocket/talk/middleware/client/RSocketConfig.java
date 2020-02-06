package com.rsocket.talk.middleware.client;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class RSocketConfig {

  @Value("${spring.rsocket.server.port}")
  private int rsocketPort;

  private final RSocketStrategies rSocketStrategies;
  private final RSocketProperties rSocketProps;

  @Bean
  public RSocketRequester rSocketRequester() {
    return RSocketRequester.builder()
            .rsocketStrategies(rSocketStrategies)
            .connectWebSocket(getURI())
            .block();
  }


  private URI getURI() {
    return URI.create(
            String.format("ws://localhost:%d%s", rsocketPort, rSocketProps.getServer().getMappingPath()));
  }
}
