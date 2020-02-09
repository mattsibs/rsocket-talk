package com.blog.rsocket.talk.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewsServer {
    public static void main(String[] args) {
        SpringApplication.run(NewsServer.class, args);
    }

}
