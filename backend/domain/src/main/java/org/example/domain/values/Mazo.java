package org.example.domain.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Mazo implements ValueObject<Mazo.props> {

    private final Set<Carta> cartas;

    private final Integer cantidad;

    public Mazo(Set<Carta> cartas) {
        this.cartas = cartas;
        this.cantidad = cartas.size();
    }

    @Override
    public props value() {
        return new props() {
            @Override
            public Set<Carta> cartas() {
                return cartas;
            }

            @Override
            public Integer cantidad() {
                return cantidad;
            }
        };
    }

    public Mazo nuevaCarta(Carta carta) {
        var newCartas = new HashSet<>(this.cartas);
        cartas.add(carta);
        return new Mazo(newCartas);

    }


    public interface props {

        Set<Carta> cartas();

        Integer cantidad();
    }
}
