import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'create-game',
  templateUrl: './create-game.component.html',
  styleUrls: ['./create-game.component.scss']
})
export class CreateGameComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToGame(): void {
    this.router.navigate(['/board']);
  }

}
