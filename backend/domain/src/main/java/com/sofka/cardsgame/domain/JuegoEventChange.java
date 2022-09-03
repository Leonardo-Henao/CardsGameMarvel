package com.sofka.cardsgame.domain;

import co.com.sofka.domain.generic.EventChange;
import com.sofka.cardsgame.domain.events.*;
import com.sofka.cardsgame.domain.values.Carta;
import com.sofka.cardsgame.domain.values.JuegoFinalizado;
import com.sofka.cardsgame.domain.values.RondaTerminada;

import java.util.HashMap;
import java.util.Objects;

public class JuegoEventChange extends EventChange {
    public JuegoEventChange(Juego juego) {

        apply((JuegoCreado event) -> {
            juego.jugadores = new HashMap<>();
            juego.jugadorPrincipal = event.getJugadorPrincipal();
        });

        apply((JugadorAgregado event) ->
                juego.jugadores.put(event.getIdentity(),
                        new Jugador(event.getIdentity(), event.getAlias(), event.getMazo())
                ));

        apply((RondaCreada event) -> {
            if (Objects.isNull(juego.tablero)) {
                throw new IllegalArgumentException("Primero, debe existir el tablero");
            }
        });

        apply((TableroCreado event) ->
                juego.tablero = new Tablero(event.getTableroId(), event.getJugadores()));

        apply((TiempoCambiadoTablero event) ->
                juego.tablero.ajustarTiempo(event.getTiempo()));

        apply((CartaPuestaEnTablero event) ->
                juego.tablero.adicionarPartida(event.getJugadorId(), event.getCarta()));

        apply((CartaQuitadaDelMazo event) ->
                juego.jugadores.get(event.getJugadorId()).quitarCartaMazo(event.getCarta()));

        apply((RondaIniciada event) -> {
            juego.ronda = juego.ronda.inicarRonda();
            juego.tablero.habilitarApuesta();
        });

        apply((RondaTerminada event) -> {
            juego.ronda = juego.ronda.terminarRonda();
            juego.tablero.inhablitarApuesta();
        });

        apply((CartasAsignadasAJugador event) -> {
            var jugador = juego.jugadores.get(event.getJugadorId());
            event.getCartasApuestas().forEach(jugador::agregarCartaMazo);
        });

        apply((JuegoFinalizado event) -> {
            juego.ganador = juego.jugadores().get(event.getJugadorId());
        });
    }
}
