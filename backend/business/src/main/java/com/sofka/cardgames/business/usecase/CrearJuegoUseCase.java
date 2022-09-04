package com.sofka.cardgames.business.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgames.business.gateway.ListaDeCartaService;
import com.sofka.cardgames.business.gateway.model.CartaMaestra;
import com.sofka.cardsgame.domain.commands.CrearJuegoCommand;
import com.sofka.cardsgame.domain.Juego;
import com.sofka.cardsgame.domain.JugadoresFactory;
import com.sofka.cardsgame.domain.values.JuegoId;
import com.sofka.cardsgame.domain.values.JugadorId;
import com.sofka.cardsgame.domain.values.Mazo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

public class CrearJuegoUseCase implements Function<Mono<CrearJuegoCommand>, Flux<DomainEvent>> {

    private final ListaDeCartaService service;

    public CrearJuegoUseCase(ListaDeCartaService service) {
        this.service = service;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearJuegoCommand> crearJuegoCommand) {
        return service.obtenerCartas().collectList()
                .flatMapMany(cartas -> crearJuegoCommand
                        .flatMapIterable(command -> {
                            var factory = new JugadoresFactory();
                            command.getJugadores()
                                    .forEach((id, alias) -> factory.agregarJugador(JugadorId.of(id), alias, generarMazo(cartas)));
var juego = new Juego(
        JuegoId.of(command.getJuegoId()),JugadorId.of(command.)
);
                        }));
    }

    private Mazo generarMazo(List<CartaMaestra> cartas) {
        return null;
    }
}
