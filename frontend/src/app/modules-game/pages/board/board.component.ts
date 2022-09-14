import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { GameWsService } from '../../services/game-ws/game-ws.service';
import { Board } from '../../services/model/board.model';
import { JuegoData } from '../../services/model/juegointerface';
import { LoginServiceService } from '../../services/user/login-service.service';

@Component({
  selector: 'board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {

  private usuarioId: string;
  juegoId!: string;
  isMainPlayer: boolean = false;
  board: Board | null = null;

  constructor(
    private game$: GameWsService,
    private activatedRoute: ActivatedRoute,
    private login$: LoginServiceService,
    private router: Router
  ) {
    this.usuarioId = this.login$.getMyUser()?.uid!;
  }

  ngOnInit() {

    this.activatedRoute.params
      .pipe(
        switchMap(({ id }) => {
          this.juegoId = id;
          return this.game$.start(id);
        })
      )
      .subscribe(() => {
        console.log(" juego id " + this.juegoId);
      });

    this.game$.start(this.juegoId).subscribe((res) => {
      console.log(" start " + res);
    });

    this.getBoardId();
  }

  getBoardId() {

    this.game$.getBoard(this.juegoId).subscribe({
      next: (res) => {

        // if (res) {
        this.isMainPlayer = res.jugadorPrincipalId == this.usuarioId;
        this.board = res;

        // } else this.router.navigate(['/create-game']);
      },
    });
  }

  initGame() {
    this.game$.startGame({ juegoId: this.juegoId }).subscribe({
      next: (res) => console.log(res),
      error: (err) => console.log(err),
    });
  }

}
