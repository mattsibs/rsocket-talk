import {Component, Input, OnInit} from '@angular/core';
import {MonitoringService} from "../monitoring.service";
import {MatSliderChange} from "@angular/material/slider";

@Component({
  selector: 'app-monitor-wrapper',
  templateUrl: './monitor-wrapper.component.html',
  styleUrls: ['./monitor-wrapper.component.scss'],
})
export class MonitorWrapperComponent implements OnInit {

  bufferSize = 0;

  speed = 0;

  newsRate = 0;

  @Input()
  private serverDisplayName: string;

  @Input()
  private serverPort: number;

  @Input()
  private socketName: string;

  @Input()
  private monitorSocketName: string;

  @Input()
  private rateSocketName: string;

  private monitoringService: MonitoringService

  ngOnInit() {
    this.monitoringService = new MonitoringService(
      this.serverPort, this.socketName, this.monitorSocketName, this.rateSocketName);

    this.monitoringService.bufferStatus().subscribe(x => {
      this.bufferSize = x;
    });
    this.monitoringService.monitorNewsRate().subscribe(x => {
      this.newsRate = x;
    })
  }

  speedChange($event: MatSliderChange) {
    this.monitoringService.setSpeed($event.value).subscribe();
  }

}
