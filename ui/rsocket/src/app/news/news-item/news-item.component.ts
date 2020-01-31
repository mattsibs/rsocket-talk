import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-news-item',
  templateUrl: './news-item.component.html',
  styleUrls: ['./news-item.component.scss']
})
export class NewsItemComponent implements OnInit {

  @Input()
  id: string;

  @Input()
  title: string;

  @Input()
  subtitle: string;

  @Input()
  image: string;

  @Input()
  content: string;

  @Output()
  likeEvent: EventEmitter<string> = new EventEmitter<string>();

  @Output()
  shareEvent: EventEmitter<string> = new EventEmitter<string>();

  constructor() { }

  ngOnInit() {
  }



}
