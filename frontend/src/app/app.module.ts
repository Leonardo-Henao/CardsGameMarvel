import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './modules-game/navbar/navbar.component';
import { CreateUserComponent } from './modules-game/forms/create-user/create-user.component';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { environment } from '../environments/environment';
import { provideAuth, getAuth } from '@angular/fire/auth';
import { HomeComponent } from './modules-game/pages/home/home.component';
import { LoginComponent } from './modules-game/pages/login/login.component';
import { provideFirestore, getFirestore } from '@angular/fire/firestore';
import { BoardComponent } from './modules-game/pages/board/board.component';
import { CardComponent } from './modules-game/pages/board/card/card.component';
import { UserComponent } from './modules-game/pages/board/user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CreateUserComponent,
    HomeComponent,
    LoginComponent,
    BoardComponent,
    CardComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    provideFirebaseApp(() => initializeApp(environment.firebase)),
    provideAuth(() => getAuth()),
    provideFirestore(() => getFirestore())
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
