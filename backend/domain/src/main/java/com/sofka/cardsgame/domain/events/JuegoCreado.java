package com.sofka.cardsgame.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardsgame.domain.Jugador;
import com.sofka.cardsgame.domain.values.JugadorId;

public class JuegoCreado extends DomainEvent {

    private final JugadorId jugadorPrincipal;

    public JuegoCreado(JugadorId jugadorPrincipal) {
        super("cardsgame.juegocreado");
        this.jugadorPrincipal = jugadorPrincipal;
    }

    public JugadorId getJugadorPrincipal() {
        return jugadorPrincipal;
    }
}
