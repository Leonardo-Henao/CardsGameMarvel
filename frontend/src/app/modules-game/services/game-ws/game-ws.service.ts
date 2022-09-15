import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { HttpClient } from '@angular/common/http';
import { LoginServiceService } from '../user/login-service.service';
import { JuegoData } from '../model/juegointerface';
import { Board } from '../model/board.model';
import { Round } from '../model/ronda.model';
import { AllDataBoard } from '../model/all.data.board';
import { Card } from '../model/card.model';
import { MazoUser } from '../model/mazo.user.model';

@Injectable({
  providedIn: 'root'
})
export class GameWsService {

  private URL_WS: String = "ws://localhost:8081/retrieve";
  private URL_HTTP: String = "http://localhost:8080";
  private webSocket!: WebSocketSubject<unknown>;

  constructor(private http: HttpClient, private login$: LoginServiceService) { }

  start(idGame: string): WebSocketSubject<unknown> {
    this.webSocket = webSocket(`${this.URL_WS}/${idGame}`);
    return this.webSocket;
  }

  close() {
    this.webSocket.closed;
  }

  createGame(body: any): Observable<object> {
    return this.http.post(`${this.URL_HTTP}/juego/crear`, { ...body });
  }

  getGames(): Observable<object> {
    return this.http.get(`${this.URL_HTTP}/juego/listar/${this.login$.getMyUser()?.uid}`);
  }

  getDataGame(): Observable<JuegoData> {
    return this.http.get(`${this.URL_HTTP}/juego/listar/${this.login$.getMyUser()?.uid}`) as Observable<JuegoData>;
  }

  startGame(body: any) {
    return this.http.post(`${this.URL_HTTP}/juego/iniciar`, body);
  }

  getBoard(gameId: string): Observable<AllDataBoard> {
    return this.http.get<AllDataBoard>(`${this.URL_HTTP}/juego/${gameId}`)
  }

  getMazoUser(idJuego: String): Observable<MazoUser> {
    return this.http.get<MazoUser>(`${this.URL_HTTP}/juego/mazo/${this.login$.getMyUser()?.uid}/${idJuego}`);
  }

}
