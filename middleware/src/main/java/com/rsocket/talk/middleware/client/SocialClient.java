package com.rsocket.talk.middleware.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class SocialClient {

  private final RestTemplate restTemplate;

  @Value("${social.client.base.url}")
  private String baseUrl;

  @SuppressWarnings("unchecked")
  public List<Share> findShares(final UUID newsid) {
    return restTemplate
        .getForEntity(baseUrl + "/shares", List.class, Map.of("newsId", newsid))
        .getBody();
  }

  @SuppressWarnings("unchecked")
  public List<Like> findLikes(final UUID newsid) {
    return restTemplate
        .getForEntity(baseUrl + "/likes", List.class, Map.of("newsId", newsid))
        .getBody();
  }

  @AllArgsConstructor(onConstructor = @__({@JsonCreator}))
  public static class Share {
    private final Long id;
    private final UUID newsId;
    private final String username;
  }

  @AllArgsConstructor(onConstructor = @__({@JsonCreator}))
  public static class Like {
    private final Long id;
    private final UUID newsId;
    private final String username;
  }
}
