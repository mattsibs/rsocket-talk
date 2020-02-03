package com.blog.rsocket.talk.server.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsItem {
    private String id;
    private String title;
    private String subtitle;
    private String image;
    private String content;
}
