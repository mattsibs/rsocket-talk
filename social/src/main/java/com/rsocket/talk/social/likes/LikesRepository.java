package com.rsocket.talk.social.likes;

import com.rsocket.talk.social.shares.Share;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Like, Long> {
    Stream<Like> findAllByNewsId(UUID newsId);

}
