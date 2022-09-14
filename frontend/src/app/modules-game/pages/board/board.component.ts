import { Component, OnInit } from '@angular/core';
import { GameWsService } from '../../services/game-ws/game-ws.service';
import { JuegoData } from '../../services/model/juegointerface';

@Component({
  selector: 'board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {

  datosJuego!: JuegoData;

  constructor(private game$: GameWsService) { 
    
  }

  ngOnInit(): void {

    this.game$.getDataGame().subscribe({
      next: (data) => {
        this.datosJuego = data as JuegoData;
        console.log(this.datosJuego);
      },
      error: (err) => { console.log(err); },
      complete: () => { console.log('done'); }
    });

    
  }

}
