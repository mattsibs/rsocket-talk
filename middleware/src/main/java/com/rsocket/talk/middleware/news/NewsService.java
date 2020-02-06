package com.rsocket.talk.middleware.news;

import com.blog.rsocket.talk.client.NewsItem;
import com.rsocket.talk.middleware.client.SocialClient;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.Flow;
import lombok.AllArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Component
@AllArgsConstructor
public class NewsService {

  private final SocialClient socialClient;
  private final RSocketRequester rSocketRequester;

  public Flux<NewsItem> getNews() {
    Flux<NewsItem> newsItemFlux =
        rSocketRequester.route("get.news").data("").retrieveFlux(NewsItem.class);

    return Flux.create(
        newsItemFluxSink -> {
          newsItemFlux.subscribeWith(
              new Subscriber<NewsItem>() {
                private Subscription subscription;

                @Override
                public void onSubscribe(Subscription subscription) {
                  subscription.request(1);
                  this.subscription = subscription;
                }

                @Override
                public void onNext(NewsItem newsItem) {
                  //                    List<SocialClient.Like> likes =
                  // socialClient.findLikes(newsItem.getId());
                  //                    List<SocialClient.Share> shares =
                  // socialClient.findShares(newsItem.getId());
                  try {
                    Thread.sleep(1000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  newsItemFluxSink.next(newsItem);
                  subscription.request(1);
                }

                @Override
                public void onError(Throwable throwable) {
                  newsItemFluxSink.error(throwable);
                }

                @Override
                public void onComplete() {
                  newsItemFluxSink.complete();
                }
              });
        });
  }
}
