package com.sofka.cardsgame.domain.values;

import co.com.sofka.domain.generic.DomainEvent;

public class JuegoFinalizado extends DomainEvent {

    private final JugadorId jugadorId;

    private final String username;

    public JuegoFinalizado(JugadorId jugadorId, String username) {
        super("cardsgame.juegofinalizado");
        this.jugadorId = jugadorId;
        this.username = username;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public String getUsername() {
        return username;
    }
}
