package com.sofka.cardsgame.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardsgame.domain.events.JugadorAgregado;
import com.sofka.cardsgame.domain.events.TableroCreado;
import com.sofka.cardsgame.domain.values.JuegoId;
import com.sofka.cardsgame.domain.events.JuegoCreado;
import com.sofka.cardsgame.domain.values.JugadorId;
import com.sofka.cardsgame.domain.values.Ronda;
import com.sofka.cardsgame.domain.values.TableroId;

import java.util.List;
import java.util.Map;

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

}
