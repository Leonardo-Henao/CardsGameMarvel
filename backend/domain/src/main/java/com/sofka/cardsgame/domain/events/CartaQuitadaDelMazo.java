package com.sofka.cardsgame.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardsgame.domain.values.Carta;
import com.sofka.cardsgame.domain.values.JugadorId;

public class CartaQuitadaDelMazo extends DomainEvent {

    private final JugadorId jugadorId;

    private final Carta carta;

    public CartaQuitadaDelMazo(JugadorId jugadorId, Carta carta) {
        super("cardsgame.cartaquitadadelmazo");
        this.jugadorId = jugadorId;
        this.carta = carta;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Carta getCarta() {
        return carta;
    }
}
