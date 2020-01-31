import {IdentitySerializer, JsonSerializer, RSocketClient} from "rsocket-core";
import RSocketWebSocketClient from "rsocket-websocket-client";
import {Observable} from "rxjs";
import {Flowable} from "rsocket-flowable";
import {Component, Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class RSocketService {

  private client: RSocketClient<any, any>;

  public init(): void {
    if (!!this.client) {
      return;
    }

    this.client = new RSocketClient({
      serializers: {
        data: JsonSerializer,
        metadata: IdentitySerializer
      },
      setup: {
        // ms btw sending keepalive to server
        keepAlive: 60000,
        // ms timeout if no keepalive response
        lifetime: 180000,
        // format of `data`
        dataMimeType: 'application/json',
        // format of `metadata`
        metadataMimeType: 'message/x.rsocket.routing.v0',
      },
      transport: new RSocketWebSocketClient({
        url: 'ws://localhost:9080/messagesocket'
      }),
    });
  }

  public requestStream(socketName: string, requestData: {}, request: number = 2147483647): Observable<any> {
    return new Observable((sink) => {

      this.client.connect().subscribe({
        onComplete: socket => {
          let socketRequestPayload = {
            data: {...requestData},
            metadata: String.fromCharCode(socketName.length) + socketName,
          };
          socket.requestStream(socketRequestPayload).subscribe({
            onComplete: () => sink.complete(),
            onError: error => sink.error(error),
            onNext: payload => sink.next(payload),
            onSubscribe: subscription => subscription.request(request),
          });
        },
        onError: error => {
          sink.error(error)
        },
        onSubscribe: cancel => {
        }
      });
    });
  }

  public channel(inputChannel: Observable<any>, socketMetadata, requestData: {}, request: number = 2147483647): Observable<any> {
    return new Observable((sink) => {

      this.client.connect().subscribe({
        onComplete: socket => {

          let payloads = toFlowable<any>(inputChannel).map(x => {
            return {
              ...x,
              metadata: socketMetadata
            }
          });

          socket.requestChannel(payloads).subscribe({
            onComplete: () => sink.complete(),
            onError: error => sink.error(error),
            onNext: payload => sink.next(payload),
            onSubscribe: subscription => subscription.request(request),
          });
        },
        onError: error => {
          sink.error(error)
        },
        onSubscribe: cancel => {
        }
      });
    });
  }

  public close(): void {
    if (this.client !== undefined) {
      this.client.close()
    }
  }
}

export function toObservable<T>(flowable: Flowable<T>, request = 2147483647) {
  return new Observable(sink => {
    flowable.subscribe({
      onError: error => sink.error(error),
      onNext: next => sink.next(next),
      onComplete: () => sink.complete(),
      onSubscribe: subscription => subscription.request(request)
    })
  })
}

export function toFlowable<T>(observable: Observable<T>): Flowable<T> {
  return new Flowable<T>(subscriber => {
    observable.subscribe({
      next: value => subscriber.onNext(value),
      complete: () => subscriber.onComplete(),
      error: err => subscriber.onError(err)
    });
  })
}
