import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServiceService } from '../../services/login-service.service';
import { DatabaseService } from '../../services/database.service';
import { user } from '@angular/fire/auth';

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
        this.databaseService.addUser({
          email: res.user.email!,
          id: res.user.uid,
          is_online: true,
          name_real: res.user.displayName!,
          photo: res.user.photoURL!,
          token_id: res.user.refreshToken
        });
        localStorage.setItem('show_logout', "1");
        this.router.navigate(['/app-home']);
      }
    );
  }

}
