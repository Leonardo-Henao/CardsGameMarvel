package com.sofka.cardsgame.domain.values;

import co.com.sofka.domain.generic.DomainEvent;

import java.util.Set;

public class RondaTerminada extends DomainEvent {

    private final TableroId tableroId;

    private final Set<JugadorId> jugadorIds;

    public RondaTerminada(TableroId tableroId, Set<JugadorId> jugadoresIds) {
        super("cardsgame.rondaterminada");
        this.tableroId = tableroId;
        jugadorIds = jugadoresIds;
    }

    public TableroId getTableroId() {
        return tableroId;
    }

    public Set<JugadorId> getJugadorIds() {
        return jugadorIds;
    }
}
