import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameWsService } from '../../services/game-ws/game-ws.service';

@Component({
  selector: 'create-game',
  templateUrl: './create-game.component.html',
  styleUrls: ['./create-game.component.scss']
})
export class CreateGameComponent implements OnInit {

  constructor(
    private router: Router,
    private ws: GameWsService
  ) { }

  ngOnInit(): void {
    this.ws.start("1234").subscribe();    
  }

  goToGame(): void {
    this.router.navigate(['/board']);
  }

}
