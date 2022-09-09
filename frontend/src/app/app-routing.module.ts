import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { canActivate, redirectLoggedInTo, redirectUnauthorizedTo } from '@angular/fire/auth-guard'
import { HomeComponent } from './modules-game/pages/home/home.component';
import { LoginComponent } from './modules-game/pages/login/login.component';
import { BoardComponent } from './modules-game/pages/board/board.component';
import { CreateGameComponent } from './modules-game/pages/create-game/create-game.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    ...canActivate(() => redirectUnauthorizedTo(['/'])),
    pathMatch: 'full'
  },
  {
    path: '',
    component: LoginComponent,
    ...canActivate(() => redirectLoggedInTo(['/home'])),
    pathMatch: 'full'
  },
  {
    path: 'board',
    component: BoardComponent,
    ...canActivate(() => redirectUnauthorizedTo(['/'])),
    pathMatch: 'full'
  },
  {
    path: 'create-game',
    component: CreateGameComponent,
    ...canActivate(() => redirectUnauthorizedTo(['/'])),
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
