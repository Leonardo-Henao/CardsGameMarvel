package com.sofka.cardsgame.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardsgame.domain.values.TableroId;

public class TiempoCambiadoTablero extends DomainEvent {

    private final TableroId tableroId;

    private final Integer tiempo;

    public TiempoCambiadoTablero(TableroId tableroId, Integer tiempo) {
        super("cardsgame.tiempocambiadotablero");
        this.tableroId = tableroId;
        this.tiempo = tiempo;
    }

    public TableroId getTableroId() {
        return tableroId;
    }

    public Integer getTiempo() {
        return tiempo;
    }
}
