import {Component, Input, OnInit} from "@angular/core";
import {MonitoringService} from "../monitoring.service";
import {MatSliderChange} from "@angular/material/slider";

@Component({
  selector: 'app-rate-wrapper',
  templateUrl: './rate.component.html'
})
export class RateComponent implements OnInit {

  speed = 2;

  @Input()
  private serverPort: number;

  @Input()
  private socketName: string;

  @Input()
  private monitorSocketName: string;


  private monitoringService: MonitoringService

  ngOnInit() {
    this.monitoringService = new MonitoringService(
      this.serverPort, this.socketName, this.monitorSocketName);

  }

  speedChange($event: MatSliderChange) {
    this.monitoringService.setSpeed($event.value).subscribe();
  }

}
