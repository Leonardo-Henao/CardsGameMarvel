package com.sofka.cardsgame.domain.values;

import co.com.sofka.domain.generic.Identity;

public class JugadorId extends Identity {

    public JugadorId() {
    }

    public JugadorId(String id) {
        super(id);
    }

    public static JugadorId of(String id) {
        return new JugadorId(id);
    }
}
