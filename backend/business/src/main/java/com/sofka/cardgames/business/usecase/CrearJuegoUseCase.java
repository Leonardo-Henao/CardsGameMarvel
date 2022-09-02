package com.sofka.cardgames.business.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgames.business.gateway.ConsultaCartasMaestrasService;
import com.sofka.cardsgame.domain.commands.CrearJuegoCommand;
import com.sofka.cardsgame.domain.Juego;
import com.sofka.cardsgame.domain.JugadoresFactory;
import com.sofka.cardsgame.domain.values.JuegoId;
import com.sofka.cardsgame.domain.values.JugadorId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class CrearJuegoUseCase implements Function<Mono<CrearJuegoCommand>, Flux<DomainEvent>> {

    private final ConsultaCartasMaestrasService service;

    public CrearJuegoUseCase(ConsultaCartasMaestrasService service) {
        this.service = service;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearJuegoCommand> crearJuegoCommand) {
        return crearJuegoCommand.flatMapMany((command) -> {
            var factory = new JugadoresFactory();

            command.getJugadores().forEach((id, alias) ->
                    factory.agregarJugador(JugadorId.of(id), alias, generarMazo())
            );
            var juego = new Juego(JuegoId.of(command.getJuegoId()), factory);
            return Flux.fromIterable(juego.getUncommittedChanges());
        });
    }
}
