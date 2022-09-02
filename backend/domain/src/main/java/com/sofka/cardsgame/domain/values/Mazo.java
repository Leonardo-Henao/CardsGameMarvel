package com.sofka.cardsgame.domain.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.HashSet;
import java.util.Set;

public class Mazo implements ValueObject<Mazo.props> {

    private final Set<Carta> cartas;

    private final Integer cantidad;

    /**
     * Constructor
     *
     * @param cartas Cartas a setear
     */
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

    /**
     * Agrega una nueva carta al mazo
     *
     * @param carta carta a agregar
     * @return nuevo mazo con la carta agregada
     */
    public Mazo nuevaCarta(Carta carta) {
        var newCartas = new HashSet<>(this.cartas);
        cartas.add(carta);
        return new Mazo(newCartas);
    }


    /**
     * Retira una carta del mazo, primero obtiene el id de la carta y despues la
     * remueve buscando entre todas las cartas con el id que coincida
     *
     * @param cartaRetirar objeto carta a retirar
     * @return nuevo mazo sin la carta retirada
     */
    public Mazo retirarCarta(Carta cartaRetirar) {
        var cartaId = cartaRetirar.value().cartaId();
        this.cartas.removeIf(
                carta -> cartaId.equals(carta.value().cartaId()));
        return new Mazo(this.cartas);
    }

    public interface props {

        Set<Carta> cartas();

        Integer cantidad();
    }
}
