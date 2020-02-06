package com.rsocket.talk.social.shares;

import com.rsocket.talk.social.shares.Share;
import com.rsocket.talk.social.shares.SharesRepository;
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
public class ShareController {

    private final SharesRepository sharesRepository;

    @PostMapping("shares")
    public void createShare(@RequestBody final Share share) {
        sharesRepository.save(share);
    }

    @GetMapping("shares")
    public List<Share> getShares(@RequestParam("newsId") final UUID newsId) {
        return sharesRepository.findAllByNewsId(newsId).collect(Collectors.toList());
    }

}
