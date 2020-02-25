import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ServerComponent} from './server/server.component';
import {MonitorWrapperComponent} from './monitor-wrapper/monitor-wrapper.component';
import {MatCardModule} from "@angular/material/card";
import {MatSliderModule} from "@angular/material/slider";
import {HttpClientModule} from "@angular/common/http";
import {GraphModule} from "../graph/graph.module";
import {RateComponent} from "./rate/rate.component";


@NgModule({
  declarations: [ServerComponent, MonitorWrapperComponent, RateComponent],
  exports: [
    MonitorWrapperComponent,
    RateComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatSliderModule,
    HttpClientModule,
    GraphModule
  ]
})
export class MonitoringModule { }
