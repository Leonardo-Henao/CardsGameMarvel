import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServiceService } from '../../services/user/login-service.service';
import JugadorModel from '../../services/model/jugador.model';
import { DatabaseService } from '../../services/user/database.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(
    private loginService$: LoginServiceService,
    private router: Router,
    private dataService$: DatabaseService) {
  }
  ngOnInit(): void {
    // throw new Error('Method not implemented.');
  }

  loginWithGoogle() {
    this.loginService$.loginWithGoogle().then(
      res => {

        var jugador: JugadorModel = {
          email: res.user.email!,
          id: res.user.uid,
          is_online: true,
          name_real: res.user.displayName!,
          photo: res.user.photoURL!,
          token_id: res.user.refreshToken,
        }

        this.dataService$.addUser(jugador)

        localStorage.setItem('show_logout', "1");
        this.router.navigate(['/home']);
      }
    );
  }
}
