import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';

@Injectable({
  providedIn: 'root'
})
export class GameWsService {

  private URL: String = "ws://localhost:8081/retrieve";
  private webSocket!: WebSocketSubject<unknown>;

  constructor(private ws: WebSocket) { }

  start(idGame: string) {
    this.webSocket = webSocket(`${this.URL}/${idGame}`);
    return this.webSocket;
  }

  close() {
    this.webSocket.closed;
  }

}
