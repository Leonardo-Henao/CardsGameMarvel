import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { GameWsService } from '../../services/game-ws/game-ws.service';
import { AllDataBoard } from '../../services/model/all.data.board';
import { Card } from '../../services/model/card.model';
import { LoginServiceService } from '../../services/user/login-service.service';

@Component({
  selector: 'board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {

  private playerId: string;
  gameId!: string;
  isMainPlayer: boolean = false;
  allDataBoard!: AllDataBoard | null;
  time = 0;
  showMyCards: boolean = false;
  cardUser!: Card[];

  constructor(
    private game$: GameWsService,
    private activatedRoute: ActivatedRoute,
    private login$: LoginServiceService,
    private router: Router
  ) {
    this.playerId = this.login$.getMyUser()?.uid!;
    this.cardUser = [];
  }

  ngOnInit() {

    this.activatedRoute.params
      .pipe(
        switchMap(({ id }) => {
          this.gameId = id;
          return this.game$.start(id);
        })
      )
      .subscribe(() => {
        console.log(" juego id " + this.gameId);
      });

    this.game$.start(this.gameId).subscribe((res) => {
      console.log(" start " + res);
    });

    this.getBoardId();
  }

  getBoardId() {

    this.game$.getBoard(this.gameId).subscribe({
      next: (res) => {

        if (res) {
          this.allDataBoard = res;
          this.time = this.allDataBoard.tiempo;
          this.getCardsUser();

        } else this.router.navigate(['/create-game']);
      },
    });
  }

  initGame() {
    this.game$.startGame({ juegoId: this.gameId }).subscribe({
      next: (res) => console.log(res),
      error: (err) => console.log(err),
    });
  }

  showCards() {
    this.showMyCards == true ? this.showMyCards = false : this.showMyCards = true;
  }

  getCardsUser() {
    this.game$.getMazoUser(this.gameId).subscribe({
      next: (res) => {

        this.cardUser = res.cartas;
        console.log(this.cardUser);
      },
      error: (err) => console.log(err),
      complete: () => console.log('complete')
    });
  }
}
