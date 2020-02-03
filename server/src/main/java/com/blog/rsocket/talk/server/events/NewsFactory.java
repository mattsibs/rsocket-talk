package com.blog.rsocket.talk.server.events;

import io.rsocket.internal.jctools.util.RangeUtil;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

public class NewsFactory {

  private static final List<String> images =
      List.of(
          "https://ichef.bbci.co.uk/news/1024/branded_news/9508/production/_103725183_p06n0x4x.jpg",
          "https://i.guim.co.uk/img/media/873cb09b105a7acabce50cc4734840b9dfef04a5/0_57_2537_1521/500.jpg?quality=85&auto=format&fit=max&s=c7cb8f4d989aed51309e2b8a41a50619",
          "https://i.kym-cdn.com/entries/icons/original/000/004/781/ainsley.jpg",
          "https://i.chzbgr.com/full/6296919808/h583DD822/flying-dirty");

  private static final List<String> people =
      List.of("Donald Trump", "Boris Johnson", "Ainsley Harriot", "JK Rowling");

  private static final List<String> verbs = List.of("cooks", "tackles");

  private static final List<String> objects =
      List.of("the defecit", "a turkey", "his goose", "school child");

  private static final List<String> location =
      List.of("in the Bahamas", "on the toilet", "eating a turkey leg");

  public static NewsItem random() {
      Random random = new Random();
      int randomPerson = randomNumber(random, people);
      int randomObject = randomNumber(random, objects);
      int randomVerb = randomNumber(random, verbs);
      int randomLocation = randomNumber(random, location);

      String title = String.format("%s %s %s %s", people.get(randomPerson), verbs.get(randomVerb), objects.get(randomObject), location.get(randomLocation));

    return NewsItem.builder().id(UUID.randomUUID().toString())
            .content(people.get(randomPerson))
            .image(images.get(randomPerson))
            .title(title)
            .subtitle(title)
            .content("Some content")
            .build();
  }

    private static int randomNumber(Random random, List<String> items) {
        return random.ints(0, items.size())
                .findFirst()
                .getAsInt();
    }
}
