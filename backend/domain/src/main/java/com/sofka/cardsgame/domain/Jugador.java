package com.sofka.cardsgame.domain;

import co.com.sofka.domain.generic.Entity;
import com.sofka.cardsgame.domain.values.Carta;
import com.sofka.cardsgame.domain.values.JugadorId;
import com.sofka.cardsgame.domain.values.Mazo;

import java.util.Objects;

public class Jugador extends Entity<JugadorId> {

    private final String email;

    private Mazo mazo;

    public Jugador(JugadorId entityId, String email, Mazo mazo) {
        super(entityId);
        this.email = Objects.requireNonNull(email);
        this.mazo = Objects.requireNonNull(mazo);
        if (mazo.value().cantidad() <= 0) {
            throw new IllegalArgumentException("El mazo debe contener cartas");
        }
    }

    public void agregarCartaMazo(Carta carta) {
        mazo = mazo.nuevaCarta(carta);
    }

    public void quitarCartaMazo(Carta carta) {
        mazo = mazo.retirarCarta(carta);
    }

    public String getEmail() {
        return email;
    }

    public Mazo getMazo() {
        return mazo;
    }
}
