package com.sofka.cardgames.business.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgames.business.gateway.JuegoDomainEventRepository;
import com.sofka.cardsgame.domain.Juego;
import com.sofka.cardsgame.domain.commands.IniciarJuegoCommand;
import com.sofka.cardsgame.domain.values.JuegoId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class IniciarJuegoUseCase extends UseCaseForCommand<IniciarJuegoCommand> {

    private final JuegoDomainEventRepository repository;

    public IniciarJuegoUseCase(JuegoDomainEventRepository repository){
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<IniciarJuegoCommand> iniciarJuegoCommand) {
        return iniciarJuegoCommand.flatMapMany((command) -> repository
                .obtenerEventosPor(command.getJuegoId())
                .collectList()
                .flatMapIterable(events -> {
                    var juego = Juego.from(JuegoId.of(command.getJuegoId()), events);
                    juego.crearTablero();
                    return juego.getUncommittedChanges();
                }));
    }
}
