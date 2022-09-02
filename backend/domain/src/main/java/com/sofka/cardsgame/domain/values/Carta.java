package com.sofka.cardsgame.domain.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Carta implements ValueObject<Carta.Props> {

    private final CartaMaestraId cartaId;
    private final Boolean estaOculta;
    private final Integer poder;
    private final Boolean estaHabilitada;

    public Carta(CartaMaestraId cartaId, Boolean estaOculta, Integer poder, Boolean estaHabilitada) {
        this.estaOculta = Objects.requireNonNull(estaOculta);
        this.poder = Objects.requireNonNull(poder);
        this.cartaId = Objects.requireNonNull(cartaId);
        this.estaHabilitada = Objects.requireNonNull(estaHabilitada);

        if (this.poder <= 0) {
            throw new IllegalArgumentException("EL PODER DE LA CARTA NO PUEDE SER NEGATIVO O CERO");
        }
    }

    @Override
    public Carta.Props value() {
        return new Props() {
            @Override
            public Integer poder() {
                return poder;
            }

            @Override
            public Boolean estaOCulta() {
                return estaOculta;
            }

            @Override
            public CartaMaestraId cartaId() {
                return cartaId;
            }

            @Override
            public Boolean estaHabilitada() {
                return estaHabilitada;
            }
        };
    }

    public interface Props {
        Integer poder();

        Boolean estaOCulta();

        CartaMaestraId cartaId();

        Boolean estaHabilitada();
    }
}
