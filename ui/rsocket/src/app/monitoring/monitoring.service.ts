import {RSocketService} from "../rsocket/rsocket.service";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

export class MonitoringService {

  private rsocketService: RSocketService;

  constructor(private port, private setSpeedSocketName, private monitorSocketName: string) {
    this.rsocketService = new RSocketService(port);
  }

  public bufferStatus(): Observable<number> {
    this.rsocketService.init();
    return this.rsocketService.requestStream(this.monitorSocketName, {})
      .pipe(map(x => x.data));
  }

  public setSpeed(speed: number): Observable<any> {
    return this.rsocketService.requestStream(this.setSpeedSocketName, {speed: speed});
  }

}
