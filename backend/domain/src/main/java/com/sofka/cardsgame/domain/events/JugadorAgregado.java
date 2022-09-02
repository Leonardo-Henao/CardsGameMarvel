package com.sofka.cardsgame.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardsgame.domain.values.JugadorId;
import com.sofka.cardsgame.domain.values.Mazo;

public class JugadorAgregado extends DomainEvent {
    private final JugadorId identity;
    private final String alias;
    private final Mazo mazo;

    public JugadorAgregado(JugadorId identity, String alias, Mazo mazo) {
        super("cardsgame.jugadoradicionado");
        this.identity = identity;
        this.alias = alias;
        this.mazo = mazo;
    }

    public JugadorId getIdentity() {
        return identity;
    }

    public String getAlias() {
        return alias;
    }

    public Mazo getMazo() {
        return mazo;
    }
}
