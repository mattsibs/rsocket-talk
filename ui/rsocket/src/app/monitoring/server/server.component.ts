import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.scss']
})
export class ServerComponent implements OnInit {

  @Input()
  sizeOfBuffer: number;

  @Input()
  bufferOverflow: number;

  constructor() { }

  ngOnInit() {
  }

}
