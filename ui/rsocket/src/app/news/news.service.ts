import {Observable} from "rxjs";
import {RSocketService} from "../rsocket/rsocket.service";
import {NewsItem} from "./news.interface";
import {map} from "rxjs/operators";

export class NewsService {

  private rsocketService: RSocketService = new RSocketService(7080);

  constructor() {
  }

  public news(): Observable<NewsItem> {
    this.rsocketService.init();
    return this.rsocketService.requestStream("get.news", {})
      .pipe(map(x => x.data));
  }
}
