package com.sofka.cardsgame.domain;

import co.com.sofka.domain.generic.Entity;
import com.sofka.cardsgame.domain.values.TableroId;
import com.sofka.cardsgame.domain.values.Carta;
import com.sofka.cardsgame.domain.values.JugadorId;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tablero extends Entity<TableroId> {

    private Integer tiempoEnSegundos;

    private Boolean estaHabilitado;

    private final Map<JugadorId, Set<Carta>> partida;

    public Tablero(TableroId tableroId, Set<JugadorId> jugadores) {
        super(tableroId);
        this.partida = new HashMap<>();
        this.estaHabilitado = false;
        jugadores.forEach(jugadorId -> partida.put(jugadorId, new HashSet<>()));
    }

    public void ajustarTiempo(Integer tiempo) {
        this.tiempoEnSegundos = tiempo;
    }

    public Integer tiempo() {
        return this.tiempoEnSegundos;
    }

    public void adicionarPartida(JugadorId jugadorId, Carta carta) {
        partida.getOrDefault(jugadorId, new HashSet<>()).add(carta);
    }

    public void quitarCarta(JugadorId jugadorId, Carta carta) {
        partida.getOrDefault(jugadorId, new HashSet<>()).remove(carta);
    }

    public void habilitarApuesta() {
        this.estaHabilitado = true;
    }

    public void inhablitarApuesta() {
        this.estaHabilitado = false;
    }

    public void reiniciarPartida() {
        partida.clear();
    }

    public Boolean estaHabilitado() {
        return estaHabilitado;
    }

    public Map<JugadorId, Set<Carta>> partida() {
        return partida;
    }

}
