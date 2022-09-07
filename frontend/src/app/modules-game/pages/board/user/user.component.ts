import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  showMyCards: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  showCards() {
    this.showMyCards == true ? this.showMyCards = false : this.showMyCards = true;
  }

}
