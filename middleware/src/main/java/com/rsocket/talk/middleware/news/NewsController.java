package com.rsocket.talk.middleware.news;

import com.blog.rsocket.talk.client.NewsItem;
import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.util.privilegedactions.NewSchema;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@AllArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @MessageMapping("get.news")
    public Flux<NewsItem> getNews() {
        return newsService.getNews();
    }

}
