package com.rsocket.talk.middleware;

import com.rsocket.talk.middleware.news.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MiddlewareApplication implements ApplicationRunner {
  public static void main(String[] args) {
    SpringApplication.run(MiddlewareApplication.class, args);
  }

  @Autowired private NewsService newsService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
//    newsService.getNews()
//            .doOnNext(x -> log.info("news item {}", x))
//            .subscribe();
  }
}
