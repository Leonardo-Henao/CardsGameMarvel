package com.sofka.cardsgame.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardsgame.domain.Jugador;
import com.sofka.cardsgame.domain.values.JugadorId;
import com.sofka.cardsgame.domain.values.TableroId;

import java.util.Map;
import java.util.Set;

public class TableroCreado extends DomainEvent {

    private final TableroId tableroId;

    private final Set<JugadorId> jugadores;

    public TableroCreado(TableroId id, Set<JugadorId> jugadores) {
        super("cardsgame.tablerocreado");
        this.tableroId = id;
        this.jugadores = jugadores;
    }

    public TableroId getTableroId() {
        return tableroId;
    }

    public Set<JugadorId> getJugadores() {
        return jugadores;
    }
}
