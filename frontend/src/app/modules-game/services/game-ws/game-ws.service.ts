import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GameWsService {

  private URL_WS: String = "ws://localhost:8081/retrieve";
  private URL_HTTP: String = "http://localhost:8080";
  private webSocket!: WebSocketSubject<unknown>;

  constructor(private http: HttpClient) { }

  start(idGame: string): WebSocketSubject<unknown> {
    this.webSocket = webSocket(`${this.URL_WS}/${idGame}`);
    return this.webSocket;
  }

  create(body: any): Observable<object> {
    return this.http.post(`${this.URL_HTTP}/juego/crear/`, { ...body });
  }

  close() {
    this.webSocket.closed;
  }

}
