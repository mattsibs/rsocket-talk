package com.rsocket.talk.social.shares;

import com.rsocket.talk.social.likes.Like;
import com.rsocket.talk.social.likes.LikesRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikesRepository likesRepository;

    @PostMapping("likes")
    public void createLike(@RequestBody final Like like) {
        likesRepository.save(like);
    }

    @GetMapping("likes")
    public List<Like> getLikes(@RequestParam("newsId") final UUID newsId) {
        return likesRepository.findAllByNewsId(newsId).collect(Collectors.toList());
    }

}
