import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {v4 as uuidv4} from 'uuid';

@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.scss']
})
export class ServerComponent implements OnInit, OnChanges {

  @Input()
  sizeOfBuffer: number;

  @Input()
  bufferOverflow: number;

  @Input()
  newsPublishRate: number;

  @Input()
  name: string;

  bufferOverflowGraphPoint = { id : '', value: new Date().getTime()};
  newsRateGraphPoint = { id : '', value: new Date().getTime()};

  constructor() { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.bufferOverflow) {
      this.bufferOverflowGraphPoint = {id: uuidv4(), value: changes.bufferOverflow.currentValue}
    }
    if (changes.newsPublishRate) {
      this.newsRateGraphPoint = {id: uuidv4(), value: changes.newsPublishRate.currentValue}
    }
  }


}
