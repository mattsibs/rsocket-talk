package com.rsocket.talk.social.shares;

import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharesRepository extends JpaRepository<Share, Long> {
    Stream<Share> findAllByNewsId(UUID newsId);
}
