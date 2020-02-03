import {Injectable} from "@angular/core";
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {RSocketService} from "../rsocket/rsocket.service";
import {NewsItem} from "./news.interface";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class NewsService {


  constructor(private rsocketService: RSocketService) {
  }

  public news(): Observable<NewsItem> {
    this.rsocketService.init();
    return this.rsocketService.requestStream("get.news", {})
      .pipe(map(x => x.data));
  }
}
