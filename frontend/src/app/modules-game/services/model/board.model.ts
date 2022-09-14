import { Round } from "./ronda.model";

export interface Board {
  ronda: Round;
  cantidadJugadores: number;
  jugadoresIniciales: string[];
  jugadorPrincipalId: String
}