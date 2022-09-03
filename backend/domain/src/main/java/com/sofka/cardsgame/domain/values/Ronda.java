package com.sofka.cardsgame.domain.values;

import co.com.sofka.domain.generic.ValueObject;
import com.sofka.cardsgame.domain.events.JugadorAgregado;

import java.util.Set;

public class Ronda implements ValueObject<Ronda.Prop> {

    private final Set<JugadorId> jugadores;

    private final Integer numeroRonda;

    private final Boolean estaIniciada;


    /**
     * Contructor para crear una ronda que todavia no esta iniciada
     *
     * @param jugadores   Id de los jugadores
     * @param numeroRonda Numero de la ronda a inicar
     */
    public Ronda(Set<JugadorId> jugadores, Integer numeroRonda) {
        this.jugadores = jugadores;
        this.numeroRonda = numeroRonda;
        this.estaIniciada = false;
    }

    /**
     * Contructor que crea una ronda y tiene la posibilidad de iniciarla
     *
     * @param jugadores    Id de los jugadores
     * @param numeroRonda  Numero de la ronda a iniciar
     * @param estaIniciada Setea si la ronda ya inicio
     */
    public Ronda(Set<JugadorId> jugadores, Integer numeroRonda, Boolean estaIniciada) {
        this.jugadores = jugadores;
        this.numeroRonda = numeroRonda;
        this.estaIniciada = estaIniciada;
    }

    @Override
    public Ronda.Prop value() {
        return new Prop() {
            @Override
            public Set<JugadorId> jugadores() {
                return jugadores;
            }

            @Override
            public Integer numeroRonda() {
                return numeroRonda;
            }

            @Override
            public Boolean estaIniciada() {
                return estaIniciada;
            }
        };
    }

    public Ronda inicarRonda() {
        return new Ronda(this.jugadores, this.numeroRonda, true);
    }

    public Ronda terminarRonda() {
        return new Ronda(this.jugadores, this.numeroRonda, false);
    }

    public interface Prop {

        Set<JugadorId> jugadores();

        Integer numeroRonda();

        Boolean estaIniciada();
    }
}
