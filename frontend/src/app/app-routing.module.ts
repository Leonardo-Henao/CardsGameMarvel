import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { canActivate, redirectLoggedInTo, redirectUnauthorizedTo } from '@angular/fire/auth-guard'
import { HomeComponent } from './modules-game/pages/home/home.component';
import { LoginComponent } from './modules-game/pages/login/login.component';

const routes: Routes = [
  {
    path: 'app-home',
    component: HomeComponent,
    ...canActivate(() => redirectUnauthorizedTo(['/'])),
    pathMatch: 'full'
  },
  {
    path: '',
    component: LoginComponent,
    ...canActivate(() => redirectLoggedInTo(['/app-home'])),
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
