import { Component, OnInit } from '@angular/core';
import {NewsItem} from "../news.interface";
import {NewsService} from "../news.service";


@Component({
  selector: 'app-news-stream',
  templateUrl: './news-stream.component.html',
  styleUrls: ['./news-stream.component.scss']
})
export class NewsStreamComponent implements OnInit {

  newsItems: NewsItem[] = [
    {
      id: '1',
      title: 'Shiba Inu',
      subtitle: 'Dog Breed',
      image: 'https://material.angular.io/assets/img/examples/shiba2.jpg',
      content: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan.\n' +
        ' A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally\n' +
        'bred for hunting.'
    },
    {
      id: '1',
      title: 'Shiba Inu',
      subtitle: 'Dog Breed',
      image: 'https://material.angular.io/assets/img/examples/shiba2.jpg',
      content: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan.\n' +
        ' A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally\n' +
        'bred for hunting.'
    },
    {
      id: '1',
      title: 'Shiba Inu',
      subtitle: 'Dog Breed',
      image: 'https://material.angular.io/assets/img/examples/shiba2.jpg',
      content: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan.\n' +
        ' A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally\n' +
        'bred for hunting.'
    },
    {
      id: '1',
      title: 'Shiba Inu',
      subtitle: 'Dog Breed',
      image: 'https://material.angular.io/assets/img/examples/shiba2.jpg',
      content: 'The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan.\n' +
        ' A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally\n' +
        'bred for hunting.'
    },
  ];

  constructor(private newsService: NewsService) { }

  ngOnInit() {
    this.newsService.news().subscribe(newsItem => {
      this.newsItems.push(newsItem);
    })
  }



}
