import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessagesComponent } from './messages.component';
import { SendmessageComponent } from './send/sendmessage.component';
import {FormsModule} from "@angular/forms";



@NgModule({
  declarations: [MessagesComponent, SendmessageComponent],
  exports: [
    MessagesComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ]
})
export class MessagesModule { }
