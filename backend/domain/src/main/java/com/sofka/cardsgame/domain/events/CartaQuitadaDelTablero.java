package com.sofka.cardsgame.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardsgame.domain.values.Carta;
import com.sofka.cardsgame.domain.values.JugadorId;
import com.sofka.cardsgame.domain.values.TableroId;

public class CartaQuitadaDelTablero extends DomainEvent {

    private final TableroId tableroId;

    private final JugadorId jugadorId;

    private final Carta carta;

    public CartaQuitadaDelTablero(TableroId tableroId, JugadorId jugadorId, Carta carta) {
        super("cardsgame.cartaquitadadeltablero");
        this.tableroId = tableroId;
        this.jugadorId = jugadorId;
        this.carta = carta;
    }

    public TableroId getTableroId() {
        return tableroId;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Carta getCarta() {
        return carta;
    }
}
