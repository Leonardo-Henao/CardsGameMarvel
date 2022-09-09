import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  urlServer: String;

  constructor(private websockets: WebSocket) {
    this.urlServer = 'wss://localhost:8080/';

  }


  crearJuego() {

  }

  crearRonda() {

  }

  iniciarRonda() {

  }

}
