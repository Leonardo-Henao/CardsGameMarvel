import { Board } from "./board.model";
import { Round } from "./ronda.model";

export interface AllDataBoard {
    tablero: Board;
    tiempo:  number;
    ronda:   Round;
}