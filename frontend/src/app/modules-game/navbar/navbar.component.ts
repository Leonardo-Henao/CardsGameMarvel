import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServiceService } from '../services/user/login-service.service';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  show_logout: Boolean = false;

  constructor(private service: LoginServiceService, private router: Router) {
    this.show_logout = localStorage.getItem('show_logout') == "1" ? true : false;
  }

  ngOnInit(): void {
  }

  signOut() {
    this.service.signOut().then(
      res => {
        localStorage.setItem('show_logout', "0");
        this.router.navigate(['/']);
      }
    )
  }

  goToHome(){
    this.router.navigate(['/home']);
  }

}
