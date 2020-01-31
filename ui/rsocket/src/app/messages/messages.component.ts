import { Component, OnInit } from '@angular/core';
import {MessagesService} from "./messages.service";

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss']
})
export class MessagesComponent implements OnInit {

  private messages: string[] = [];

  constructor(private messageService: MessagesService) { }

  ngOnInit() {
    this.messageService.messages().subscribe(message => {
      this.messages.push(message);
    })
  }

}
