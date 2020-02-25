import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {RsocketModule} from "./rsocket/rsocket.module";
import {MessagesModule} from "./messages/messages.module";
import {NewsModule} from "./news/news.module";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MonitoringModule} from "./monitoring/monitoring.module";
import {MatCardModule} from "@angular/material/card";

@NgModule({
  declarations: [
    AppComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        RsocketModule,
        MessagesModule,
        NewsModule,
        BrowserAnimationsModule,
        MonitoringModule,
        MatCardModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
