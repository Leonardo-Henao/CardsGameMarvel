package com.sofka.cardsgame.domain;

import com.sofka.cardsgame.domain.values.JugadorId;
import com.sofka.cardsgame.domain.values.Mazo;

import java.util.HashSet;
import java.util.Set;

public class JugadoresFactory {
    private final Set<Jugador> jugadores;

    public JugadoresFactory() {
        this.jugadores = new HashSet<>();
    }

    public void agregarJugador(JugadorId id, String alias, Mazo mazo){
       this.jugadores.add(new Jugador(id, alias, mazo)) ;
    }

    protected Set<Jugador> getJugadores() {
        return jugadores;
    }
}
