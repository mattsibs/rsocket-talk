package com.blog.rsocket.talk.client;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewsStory {
    private final NewsItem newsItem;
    private final List<Like> likes;
    private final List<Share> shares;
}
