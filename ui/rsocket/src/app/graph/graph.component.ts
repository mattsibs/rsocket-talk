import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import * as Highcharts from 'highcharts';
import {BehaviorSubject, Subject} from "rxjs";

export interface DataPoint {
  x: number;
  y: number;
}

@Component({
  selector: 'app-dynamic-graph',
  templateUrl: './graph.component.html'
})
export class GraphComponent implements OnInit, OnChanges {
  @Input()
  private title: string;
  @Input()
  private series1Points: {}[] = [];
  @Input()
  private series1Point: { id: string, value: number };
  @Input()
  private series1Name: string;

  private dpsLength = 0;
  private maxEvents = 50;
  rerender: Subject<boolean> = new BehaviorSubject(false);

  Highcharts: typeof Highcharts = Highcharts;
  chartOptions: Highcharts.Options = null;

  ngOnInit() {
    this.chartOptions = this.generateChartOptions();
    this.rerender.next(true);
  }

  private generateChartOptions(): Highcharts.Options {
    return {
      title: {
        text: this.title
      },
      series: [{
        data: [],
        type: 'line',
        name: this.series1Name
      }]
    };
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    if (changes.series1Point) {
      this.series1Points.push(changes.series1Point.currentValue.value);
      this.dpsLength = this.series1Points.length;
    }

    if (this.dpsLength > this.maxEvents) {
      this.series1Points.shift();
    }

    this.rerender.next(false);
    this.chartOptions = {
      ...this.generateChartOptions(),
      series: [
        {
          data: this.series1Points,
          type: 'line'
        }
        ]
    };
    this.rerender.next(true);
  }


}
