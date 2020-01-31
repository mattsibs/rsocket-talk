import { Injectable } from '@angular/core';
import {RSocketService} from "../rsocket/rsocket.service";
import {BehaviorSubject, Observable, Subject} from "rxjs";

@Injectable({
  providedIn: "root"
})
export class MessagesService {

  private messageSubject: Subject<string> = new BehaviorSubject("Everyone say hello to new user");

  constructor(private rsocketService: RSocketService) {
    // this.rsocketService.channel(this.messageSubject.asObservable(), 'send.message', {});
  }

  public messages(): Observable<string> {
    this.rsocketService.init();
    return this.rsocketService.requestStream("get.messages", {});
  }

  sendMessage(text: string) {
    this.messageSubject.next(text);
  }
}
