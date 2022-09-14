import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { HttpClient } from '@angular/common/http';
import { LoginServiceService } from '../user/login-service.service';
import { JuegoData } from '../model/juegointerface';

@Injectable({
  providedIn: 'root'
})
export class GameWsService {

  private URL_WS: String = "ws://localhost:8081/retrieve";
  private URL_HTTP: String = "http://localhost:8082";
  private webSocket!: WebSocketSubject<unknown>;

  constructor(private http: HttpClient, private login$: LoginServiceService) { }

  start(idGame: string): WebSocketSubject<unknown> {
    this.webSocket = webSocket(`${this.URL_WS}/${idGame}`);
    return this.webSocket;
  }

  close() {
    this.webSocket.closed;
  }

  create(body: any): Observable<object> {
    return this.http.post(`${this.URL_HTTP}/juego/crear`, { ...body });
  }

  getGames(): Observable<object> {
    return this.http.get(`${this.URL_HTTP}/juego/listar/${this.login$.getMyUser()?.uid}`);
  }

  getDataGame(): Observable<object> {
    return this.http.get(`${this.URL_HTTP}/juego/listar/${this.login$.getMyUser()?.uid}`);
  }

}
