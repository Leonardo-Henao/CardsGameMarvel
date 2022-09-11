import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameWsService } from '../../services/game-ws/game-ws.service';
@Component({
  selector: 'create-game',
  templateUrl: './create-game.component.html',
  styleUrls: ['./create-game.component.scss']
})
export class CreateGameComponent implements OnInit {

  dataGames: object;

  constructor(
    private router: Router,
    private ws: GameWsService,
  ) {
    this.dataGames = this.ws.getGames().subscribe();
    console.log(this.dataGames);
  }

  ngOnInit(): void {
  }

  goToGame(): void {
    this.router.navigate(['/board']);
  }

}
