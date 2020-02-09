import {Component, OnInit} from '@angular/core';
import {NewsItem} from "../news.interface";
import {NewsService} from "../news.service";


@Component({
  selector: 'app-news-stream',
  templateUrl: './news-stream.component.html',
  styleUrls: ['./news-stream.component.scss'],
  providers: [NewsService]
})
export class NewsStreamComponent implements OnInit {

  private maxNewsItems = 4;

  newsItems: NewsItem[] = [];

  constructor(private newsService: NewsService) { }

  ngOnInit() {
    this.newsService.news().subscribe(newsItem => {
      if (this.newsItems.length > this.maxNewsItems) {
        this.newsItems.shift();
      }
      this.newsItems.push(newsItem);
    })
  }

}
