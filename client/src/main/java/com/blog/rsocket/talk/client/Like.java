package com.blog.rsocket.talk.client;

import java.util.UUID;
import lombok.Data;

@Data
public class Like {
    private Long id;
    private UUID newsId;
    private String username;
}
