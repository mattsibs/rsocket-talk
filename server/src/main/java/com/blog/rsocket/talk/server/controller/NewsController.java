package com.blog.rsocket.talk.server.controller;

import com.blog.rsocket.talk.client.NewsItem;
import com.blog.rsocket.talk.server.events.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class NewsController {
  private final NewsService newsService;

  @MessageMapping("get.news")
  public Flux<NewsItem> subscribe() {
    return newsService.newsFluxWithBackPressure();
  }
}
