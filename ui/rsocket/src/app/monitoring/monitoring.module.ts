import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ServerComponent} from './server/server.component';
import { MonitorWrapperComponent } from './monitor-wrapper/monitor-wrapper.component';
import {MatCardModule} from "@angular/material/card";
import {MatSliderModule} from "@angular/material/slider";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [ServerComponent, MonitorWrapperComponent],
  exports: [
    MonitorWrapperComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatSliderModule,
    HttpClientModule
  ]
})
export class MonitoringModule { }
