package com.blog.rsocket.talk.client;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsItem {
    private UUID id;
    private String title;
    private String subtitle;
    private String image;
    private String content;
}
