package com.sofka.cardsgame.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardsgame.domain.values.Carta;
import com.sofka.cardsgame.domain.values.JugadorId;

import java.util.Set;

public class CartasAsignadasAJugador extends DomainEvent {

    private final JugadorId jugadorId;

    private final Integer puntos;

    private final Set<Carta> cartasApuestas;

    public CartasAsignadasAJugador(JugadorId jugadorId, Integer puntos, Set<Carta> cartasApuestas) {
        super("cardsgame.cartasasignadasajugador");
        this.jugadorId = jugadorId;
        this.puntos = puntos;
        this.cartasApuestas = cartasApuestas;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public Set<Carta> getCartasApuestas() {
        return cartasApuestas;
    }
}
