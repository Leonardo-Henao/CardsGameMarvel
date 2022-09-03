package com.sofka.cardsgame.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardsgame.domain.events.*;
import com.sofka.cardsgame.domain.values.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Juego extends AggregateEvent<JuegoId> {
    protected Map<JugadorId, Jugador> jugadores;
    protected Tablero tablero;

    protected Jugador ganador;

    protected Ronda ronda;

    protected JugadorId jugadorPrincipal;

    public Juego(JuegoId juegoId, JugadorId jugadorId, JugadoresFactory jugadoresFactory) {
        super(juegoId);
        appendChange(new JuegoCreado(jugadorId)).apply();
        jugadoresFactory.getJugadores()
                .forEach(jugador -> appendChange(new JugadorAgregado(
                        jugador.identity(),
                        jugador.getEmail(),
                        jugador.getMazo())));
        subscribe(new JuegoEventChange(this));
    }

    private Juego(JuegoId entityId) {
        super(entityId);
        subscribe(new JuegoEventChange(this));
    }

    public static Juego from(JuegoId juegoId, List<DomainEvent> events) {
        var juego = new Juego(juegoId);
        events.forEach(juego::applyEvent);
        return juego;
    }

    public void crearTablero() {
        var id = new TableroId();
        appendChange(new TableroCreado(id, jugadores.keySet())).apply();
    }

    public void crearRonda(Ronda ronda, Integer tiempo) {
        appendChange(new RondaCreada(ronda, tiempo)).apply();
    }

    public void cambiarTiempotablero(TableroId tablero, Integer tiempo) {
        appendChange(new TiempoCambiadoTablero(tablero, tiempo)).apply();
    }

    public void ponerCartaEnTablero(TableroId tableroId, JugadorId jugadorId, Carta carta) {
        appendChange(new CartaPuestaEnTablero(tableroId, jugadorId, carta)).apply();
        appendChange(new CartaQuitadaDelMazo(jugadorId, carta)).apply();
    }

    public void quitarCartaTablero(TableroId tableroId, JugadorId jugadorId, Carta carta) {
        appendChange(new CartaQuitadaDelTablero(tableroId, jugadorId, carta)).apply();
    }

    public void iniciarRonda() {
        appendChange(new RondaIniciada()).apply();
    }

    public void asignarCartasAJugador(JugadorId jugadorId, Integer puntos, Set<Carta> cartasApuestas) {
        appendChange(new CartasAsignadasAJugador(jugadorId, puntos, cartasApuestas)).apply();
    }

    public void terminarRonda(TableroId tableroId, Set<JugadorId> jugadoresIds) {
        appendChange(new RondaTerminada(tableroId, jugadoresIds)).apply();
    }

    public void finalizarJuego(JugadorId jugadorId, String username) {
        appendChange(new JuegoFinalizado(jugadorId, username)).apply();
    }

    public Ronda ronda() {
        return ronda;
    }

    public Tablero tablero() {
        return tablero;
    }

    public Map<JugadorId, Jugador> jugadores() {
        return jugadores;
    }
}
