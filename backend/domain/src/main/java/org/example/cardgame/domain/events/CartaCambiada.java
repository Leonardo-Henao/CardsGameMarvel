package org.example.cardgame.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.cardgame.domain.values.JugadorId;
import org.example.cardgame.domain.values.Mazo;

public class CartaCambiada extends DomainEvent {

    private JugadorId jugadorId;
    private Mazo mazo;

    public CartaCambiada(JugadorId jugadorId, Mazo mazo) {
        super("cardgame.cartacambiada");
        this.jugadorId = jugadorId;
        this.mazo = mazo;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Mazo getMazo() {
        return mazo;
    }
}
