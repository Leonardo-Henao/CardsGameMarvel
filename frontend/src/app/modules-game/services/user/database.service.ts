import { Injectable } from '@angular/core';
import { Firestore, collection, setDoc, collectionData, where, getDocs, onSnapshot } from "@angular/fire/firestore";
import { doc, query } from 'firebase/firestore';
import { Observable } from 'rxjs';
import JugadorModel from '../model/jugador.model';

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {

  constructor(private firestore: Firestore) {
  }

  addUser(jugador: JugadorModel) {
    const refjugadores = doc(collection(this.firestore, 'jugadores'), jugador.id);
    return setDoc(refjugadores, jugador);
  }

  getDatabase(): Observable<JugadorModel[]> {
    const refjugadores = collection(this.firestore, 'jugadores')
    const query_personal = query(refjugadores, where("is_online", "==", true));
    return collectionData(query_personal, { idField: 'id' }) as Observable<JugadorModel[]>;
  }

}
