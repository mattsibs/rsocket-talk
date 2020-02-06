package com.rsocket.talk.social.shares;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHARE")
public class Share {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private UUID newsId;

    @Column
    private String username;

}
