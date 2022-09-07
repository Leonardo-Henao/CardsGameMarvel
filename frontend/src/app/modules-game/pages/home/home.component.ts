import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DatabaseService } from '../../services/database.service';
import JugadorModel from '../../services/model/jugador.model';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  jugadores!: JugadorModel[];

  constructor(
    private dbServices: DatabaseService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.dbServices.getDatabase().subscribe({
      next: (res) => this.jugadores = res,
      error: (err) => console.log(err)
    })
  }

  goToBoard(): void {
    this.router.navigate(['/app-board']);
  }
}
