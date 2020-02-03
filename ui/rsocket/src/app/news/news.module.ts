import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewsStreamComponent } from './news-stream/news-stream.component';
import {MatCardModule} from "@angular/material/card";
import { NewsItemComponent } from './news-item/news-item.component';
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import { NewsWrapperComponent } from './news-wrapper/news-wrapper.component';



@NgModule({
    declarations: [NewsStreamComponent, NewsItemComponent, NewsWrapperComponent],
  exports: [
    NewsStreamComponent,
    NewsWrapperComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule
  ]
})
export class NewsModule { }
