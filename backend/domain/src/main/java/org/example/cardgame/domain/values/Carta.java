package org.example.cardgame.domain.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

/**
 * The type Carta.
 */
public class Carta implements ValueObject<Carta.Props>, Comparable<Carta> {

    private final CartaMaestraId cartaId;
    private final Boolean estaOculta;
    private final Boolean estaHabilitada;
    private final Integer poder;

    private final String name;

    private final String url;

    public Carta(CartaMaestraId cartaId, Integer poder, Boolean estaOculta, Boolean estaHabilitada,
                 String name, String url) {
        this.cartaId = cartaId;
        this.estaOculta = estaOculta;
        this.estaHabilitada = estaHabilitada;
        this.poder = poder;
        this.name = name;
        this.url = url;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public CartaMaestraId cartaId() {
                return cartaId;
            }

            @Override
            public Integer poder() {
                return poder;
            }

            @Override
            public Boolean estaOculta() {
                return estaOculta;
            }

            @Override
            public Boolean estaHabilitada() {
                return estaHabilitada;
            }

            @Override
            public String name() {
                return name;
            }

            @Override
            public String url() {
                return url;
            }
        };
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Carta carta = (Carta) object;
        return Objects.equals(cartaId, carta.cartaId) &&
                Objects.equals(estaOculta, carta.estaOculta) &&
                Objects.equals(estaHabilitada, carta.estaHabilitada) &&
                Objects.equals(poder, carta.poder) &&
                Objects.equals(name, carta.name) &&
                Objects.equals(url, carta.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartaId, estaOculta, estaHabilitada, poder);
    }

    @Override
    public int compareTo(Carta carta) {
        return this.poder - carta.poder;
    }


    /**
     * The interface Props.
     */
    public interface Props {

        CartaMaestraId cartaId();

        Integer poder();

        Boolean estaOculta();

        Boolean estaHabilitada();

        String name();

        String url();
    }
}
