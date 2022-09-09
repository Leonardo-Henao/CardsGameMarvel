import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DatabaseService } from '../../services/user/database.service';
import JugadorModel from '../../services/model/jugador.model';
import { AbstractControl, FormControl, FormGroup, NgModel, ValidationErrors, Validators } from '@angular/forms';
@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  jugadores!: JugadorModel[];
  formPlayers!: FormGroup;

  constructor(
    private dbServices: DatabaseService,
    private router: Router) {

    this.formPlayers = new FormGroup({
      jugadores: new FormControl(
        "", [Validators.required, this.jugadoresMinimos]
      )
    });

  }

  jugadoresMinimos(control: AbstractControl): ValidationErrors | null {
    return control.value.length >= 2 ? null : { erro: "Debe seleccionar minimo dos jugadores" };
  }

  ngOnInit(): void {
    this.dbServices.getDatabase().subscribe({
      next: (res) => this.jugadores = res,
      error: (err) => console.log(err)
    })
  }

  goToCreateGame(): void {
    this.router.navigate(['/create-game']);
  }
}
