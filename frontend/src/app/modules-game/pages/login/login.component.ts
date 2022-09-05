import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServiceService } from '../../services/login-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private service: LoginServiceService, private router: Router) {
  }
  ngOnInit(): void {
   // throw new Error('Method not implemented.');
   console.log('entro a login');
  }

  loginWithGoogle() {
    this.service.loginWithGoogle().then(
      res => {
        console.log(res);
        this.router.navigate(['/app-home']);
      }
    );
  }

}
