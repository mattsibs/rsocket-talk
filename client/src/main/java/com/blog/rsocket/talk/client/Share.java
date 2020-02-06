package com.blog.rsocket.talk.client;

import java.util.UUID;
import lombok.Data;

@Data
public class Share {
    private Long id;
    private UUID newsId;
    private String username;
}
