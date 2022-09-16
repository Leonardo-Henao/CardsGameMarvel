package org.example.cardgame.usecase.usecase;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.cardgame.domain.Juego;
import org.example.cardgame.domain.command.CambiarCartaCommand;
import org.example.cardgame.domain.values.JuegoId;
import org.example.cardgame.domain.values.JugadorId;
import org.example.cardgame.usecase.gateway.JuegoDomainEventRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CambiarCartaUseCase extends UseCaseForCommand<CambiarCartaCommand> {

    private final JuegoDomainEventRepository repository;

    public CambiarCartaUseCase(JuegoDomainEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CambiarCartaCommand> cambiarCartaCommandMono) {
        return cambiarCartaCommandMono.flatMapMany((command) -> repository
                .obtenerEventosPor(command.getJuegoId().value())
                .collectList()
                .flatMapIterable(events -> {
                    var juego = Juego.from(JuegoId.of(command.getJuegoId().value()), events);
                    var jugadorId = JugadorId.of(command.getJugadorId().value());
                    var cartasDelJugador = juego.jugadores().get(jugadorId).mazo().value().cartas();
                    cartasDelJugador.remove(command.getCardOld());
                    cartasDelJugador.add(command.getCardNew());

                    var mazo = juego.jugadores().get(jugadorId).mazo();
                    mazo.retirarCarta(command.getCardOld());
                    mazo.nuevaCarta(command.getCardNew());

                    juego.cambiarCartaJugador(jugadorId, mazo);
                    return juego.getUncommittedChanges();
                }));
    }
}
