import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServiceService } from '../../services/login-service.service';
import { DatabaseService } from '../../services/database.service';
import { user } from '@angular/fire/auth';
import JugadorModel from '../../services/model/jugador.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(
    private service: LoginServiceService,
    private router: Router,
    private databaseService: DatabaseService) {
  }
  ngOnInit(): void {
    // throw new Error('Method not implemented.');
  }

  loginWithGoogle() {
    this.service.loginWithGoogle().then(
      res => {

        // var jugador: JugadorModel = {
        //   email: res.user.email!,
        //   id: res.user.uid,
        //   is_online: true,
        //   name_real: res.user.displayName!,
        //   photo: res.user.photoURL!,
        //   token_id: res.user.refreshToken
        // }

        var jugador: JugadorModel = {
          email: "leonardofhenao.085@gmail.com",
          id: "7eBjhwU7ZeMUgCLSMZ3ZGlgdL343",
          is_online: true,
          name_real: "leonardo henao",
          photo: "https://lh3.googleusercontent.com/a-/AFdZucplJYWGZdpwMo9rv2RMXUtapM6UW3ipZKZU_TscH-k=s96-c",
          token_id: "AOEOulaW4iJhRtxRxF2Z0X-mJXaNZCsgWVAa3SSeO5faEJlz6qgZHbUneI2BwDB2CK7Ow1Rnr1RLdMnTdd1WI2EHgLO4N3x0QeaoipTUgmaz4rE1zK0t_Wz-GJKf8nHUBYCtIMlx0mP5pK-w-3hCFm5cJbQCgItMUkwoXVckqmpEUmoTF-XmID6nWybfzBJl-PAgR64lee9-CnR7Qa7IaqvZSKm9IfBPvq5EWI_k5YPlben1UoFjpujjG5Rb-La52_VAW2Clov-8DW9yknxhZh0wqlBVIkorjzagptPoZkHV1zaTXQnCqQ96EgDSWDreBxeR7b7m0vhCP8Jah5pmE8axh6kYn04tL00OhGdNMti_WViaUFMv6AWPor3NAWBqb_sJLPlUvD8_urVaumhyQ8ny-jROX1x6sB2djkifJbQp6qa9VYxNo2Bi-YXjCmc5T0acsGT9yecV"
        }

        console.log(jugador);

        //this.databaseService.addUser(jugador);

        localStorage.setItem('show_logout', "1");
        this.router.navigate(['/app-home']);
      }
    );
  }

}
