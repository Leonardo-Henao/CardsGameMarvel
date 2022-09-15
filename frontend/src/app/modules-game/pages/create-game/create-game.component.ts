import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameWsService } from '../../services/game-ws/game-ws.service';
import { JuegoData } from '../../services/model/juegointerface';
import { JugadorSimple } from '../../services/model/jugadorsimpleinterface';
@Component({
  selector: 'create-game',
  templateUrl: './create-game.component.html',
  styleUrls: ['./create-game.component.scss']
})
export class CreateGameComponent implements OnInit {

  dataGames: any;

  constructor(
    private router: Router,
    private ws: GameWsService,
  ) {
    this.dataGames = [];
  }

  ngOnInit(): void {
    this.ws.getGames().subscribe({
      next: (response) => {
        this.dataGames = response;
      },
      error: (error) => console.log(error),
    });
  }

  goToGame(idGame: string): void {
    this.ws.startGame({ juegoId: idGame }).subscribe({
      next: (res) => {
        this.ws.start(idGame).subscribe({
          next: (res) => console.log(res),
        });
      },
      complete: () => {

        this.router.navigate([`/board/${idGame}`]);
      },
    });
  }

  getNameCreator(dataGame: JuegoData) {
    return dataGame.jugadores[dataGame.uid].alias;
  }

  getNameJugadores(player: JugadorSimple) {
    return player.alias;
  }
}
