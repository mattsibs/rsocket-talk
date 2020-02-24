package com.rsocket.talk.middleware.news;

import com.blog.rsocket.talk.client.NewsItem;
import com.rsocket.talk.middleware.client.SocialClient;
import java.util.concurrent.atomic.AtomicLong;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@AllArgsConstructor
public class NewsService {

  private final SocialClient socialClient;
  private final RSocketRequester rSocketRequester;
  final AtomicLong consumptionRatePerSecond = new AtomicLong(1);

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
                  log.info("Subcribed to news middleware {}", subscription);
                  subscription.request(1);
                  this.subscription = subscription;
                }

                @Override
                public void onNext(NewsItem newsItem) {
                  long rate = consumptionRatePerSecond.get();
                    log.info("Rate {}", rate);

                  try {
                      long sleepRate = 1000 / rate;
                      log.info("Sleeping for {} millis", sleepRate);
                      Thread.sleep(sleepRate);
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
