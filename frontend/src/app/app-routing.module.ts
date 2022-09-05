import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { canActivate, redirectLoggedInTo, redirectUnauthorizedTo } from '@angular/fire/auth-guard'
import { HomeComponent } from './modules-game/pages/home/home.component';
import { LoginComponent } from './modules-game/pages/login/login.component';

const routes: Routes = [
  {
    path: 'app-home',
    component: HomeComponent,
    ...canActivate(() => redirectLoggedInTo(['/app-home']))
  },
  {
    path: 'app-login',
    component: LoginComponent,
    ...canActivate(() => redirectUnauthorizedTo(['/app-login']))
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
