import { Component, OnInit } from '@angular/core';
import {MessagesService} from "../messages.service";

@Component({
  selector: 'app-sendmessage',
  templateUrl: './sendmessage.component.html',
  styleUrls: ['./sendmessage.component.scss']
})
export class SendmessageComponent implements OnInit {

  text: string;

  constructor(private messagesService: MessagesService) { }

  ngOnInit() {
  }

  sendMessage() {
    this.messagesService.sendMessage(this.text);
  }

}
