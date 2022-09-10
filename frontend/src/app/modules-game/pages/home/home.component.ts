import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DatabaseService } from '../../services/user/database.service';
import { LoginServiceService } from '../../services/user/login-service.service';
import { GameWsService } from '../../services/game-ws/game-ws.service';
import JugadorModel from '../../services/model/jugador.model';
import { AbstractControl, FormControl, FormGroup, NgModel, ValidationErrors, Validators } from '@angular/forms';
import { v4 as uuidv4, v4 } from 'uuid';
import { User } from 'firebase/auth';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  jugadores!: JugadorModel[];
  formPlayers!: FormGroup;

  command: any;
  gameId: string;
  principalPlayer: User | null;

  constructor(
    private dbServices$: DatabaseService,
    private router: Router,
    private login$: LoginServiceService,
    private gameService$: GameWsService) {

    this.formPlayers = new FormGroup({
      jugadores: new FormControl(
        "", [Validators.required, this.jugadoresMinimos]
      )
    });

    // ################ HABILITAR #########################

    //    this.gameId = v4();

    // ################ HABILITAR #########################

    this.gameId = "7c89ab19-d6e8-4872-b603-373c05dee80a";
    

    this.principalPlayer = this.login$.getMyUser();
    this.command = {
      juegoId: this.gameId,
      jugadores: { [this.principalPlayer!.uid]: this.principalPlayer!.displayName },
      jugadorPrincipalId: this.principalPlayer!.uid
    }
  }

  jugadoresMinimos(control: AbstractControl): ValidationErrors | null {
    return control.value.length >= 2 ? null : { erro: "Debe seleccionar minimo dos jugadores" };
  }

  ngOnInit(): void {
    this.dbServices$.getDatabase().subscribe({
      next: (res) => this.jugadores = res,
      error: (err) => console.log(err)
    })
  }

  goToCreateGame(): void {

    const jugadoresParaJugar = this.formPlayers.value.jugadores as User[];
    const playersSend = this.generatePlayersCommand(jugadoresParaJugar);

    this.command = {
      ...this.command,
      jugadores: { ...this.command.jugadores, ...playersSend }
    }

    this.gameService$.create(this.command).subscribe({
      next: (response) => console.log(response),
      error: (response) => console.log(response),
      complete: () => {
        this.router.navigate(['/create-game']);
      }
    })
  }

  private generatePlayersCommand(jugadores: User[]) {
    return jugadores.reduce((previous: any, current: any) => {
      return (previous = {
        ...previous,
        [current.id]: current.name_real,
      });
    }, {});
  }
}
