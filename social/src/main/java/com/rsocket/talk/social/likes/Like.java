package com.rsocket.talk.social.likes;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOCIAL_LIKE")
public class Like {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private UUID newsId;
}
