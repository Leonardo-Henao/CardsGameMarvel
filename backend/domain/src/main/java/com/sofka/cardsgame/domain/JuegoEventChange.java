package com.sofka.cardsgame.domain;

import co.com.sofka.domain.generic.EventChange;
import com.sofka.cardsgame.domain.events.JuegoCreado;
import com.sofka.cardsgame.domain.events.JugadorAgregado;

import java.util.HashMap;

public class JuegoEventChange extends EventChange {
    public JuegoEventChange(Juego juego) {

        // Evento que se lanza al crearse el juego
        apply((JuegoCreado event) -> {
            juego.jugadores = new HashMap<>();
            juego.jugadorPrincipal = event.getJugadorPrincipal();
        });


        apply((JugadorAgregado event) -> {
            juego.jugadores.put(event.getIdentity(),
                    new Jugador(event.getIdentity(), event.getAlias(), event.getMazo())
            );
        });
    }
}
