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

  mobalGanador = false;
  mobalPerdedor = false;
  mobalWinnerGame = false;

  gameId!: string;
  UserId!: string;

  isMainPlayer: boolean = false;
  allDataBoard!: AllDataBoard | null;
  time = 0;
  showMyCards: boolean = false;

  cardsUser: Card[] = [];
  cardsBoard: Card[] = [];
  cardSelected: boolean = false;

  roundStarted = false;
  roundNumber: number = 0;
  roundWinner!: string;

  gamePlayers: number = 0;
  winnerPlayer: string = "leo";
  thereAWinner: boolean = false;

  constructor(
    private game$: GameWsService,
    private router: Router,
    private login$: LoginServiceService,
    private activateRouter: ActivatedRoute,
  ) {
    this.cardsUser = [];
    this.UserId = this.login$.getMyUser()!.uid;
  }

  ngOnInit() {

    this.activateRouter.params
      .pipe(switchMap(({ id }) => {
        this.gameId = id;

        this.getBoard();
        this.game$.startGame({ juegoId: this.gameId }).subscribe({});

        return this.game$.start(id);
      })
      ).subscribe({

        next: (event: any) => {

          switch (event.type) {

            case 'cardgame.tiempocambiadodeltablero':
              this.time = event.tiempo;
              if (this.time == 1 && this.cardSelected == null) this.setRandomCard();
              break;

            case 'cardgame.rondainiciada':
              this.roundStarted = true;
              this.time = event.tiempo;
              this.roundNumber = event.ronda.numero;
              break;

            case 'cardgame.ponercartaentablero':
              const card: Card = {
                cartaId: event.carta.cartaId,
                estaOculta: event.carta.estaOculta,
                estaHabilitada: event.carta.estaHabilitada,
                poder: event.carta.poder,
                url: event.carta.url,
                nombre: event.carta.nombre
              }
              this.cardsBoard.push(card);
              break;

            case 'cardgame.cartaquitadadelmazo':
              this.cardsUser = this.cardsUser
                .filter((item) => item.cartaId !== event.carta.cartaId.uuid)
              break;

            case 'cardgame.rondacreada':
              this.time = event.tiempo;
              this.gamePlayers = event.ronda.jugadores.length
              this.roundNumber = event.ronda.numero;
              break;

            case 'cardgame.juegofinalizado':
              this.winnerPlayer = event.alias;
              this.thereAWinner = true;
              this.roundWinner = event.alias;
              this.mobalWinnerGame = true;
              break;

            case 'cardgame.rondaterminada':
              this.cardsBoard = [];
              break;

            case 'cardgame.cartasasignadasajugador':

              if (event.ganadorId.uuid === this.UserId) {
                event.cartasApuesta.forEach((carta: any) => {

                  console.log(carta);

                  const card: Card = {
                    cartaId: carta.cartaId.uuid,
                    estaOculta: carta.estaOculta,
                    estaHabilitada: carta.estaHabilitada,
                    poder: carta.poder,
                    url: carta.url,
                    nombre: carta.name
                  }
                  this.cardsUser.push(card);
                });

                this.mobalGanador = true;

              } else this.mobalPerdedor = true;

              break;

          }
        }
      });
  }

  hiddenMobalGanador() { this.mobalGanador = false; }

  hiddenMobalPerdedor() { this.mobalPerdedor = false; }

  hiddenMobalWinnerGame() {
    this.mobalWinnerGame = false;
    this.router.navigate(['/home']);
  }

  getBoard() {
    this.game$.getBoard(this.gameId).subscribe({
      next: (res) => {

        if (res) {
          this.allDataBoard = res;
          this.time = this.allDataBoard.tiempo;
          this.getCardsUser();

        } else this.router.navigate(['/home']);
      },
    });
  }

  startRound() {
    this.game$.startRound(this.gameId).subscribe();
  }

  showCards() {
    this.showMyCards == true ? this.showMyCards = false : this.showMyCards = true;
  }

  getCardsUser() {
    this.game$.getMazoUser(this.gameId).subscribe({
      next: (res) => this.cardsUser = res.cartas,
      error: (err) => console.log(err)
    });
  }


  updateTime(time: number) {
    this.time = time;
  }

  putCard(card: Card) {

    this.cardSelected = true;
    this.showCards();

    const body = {
      jugadorId: this.UserId,
      cartaId: card.cartaId,
      juegoId: this.gameId
    }
    this.game$.putUserCardToBoard(body).subscribe();
  }

  setRandomCard() {
    this.putCard(this.cardsUser[Math.floor(Math.random() * this.cardsUser.length)]);
  }

}
