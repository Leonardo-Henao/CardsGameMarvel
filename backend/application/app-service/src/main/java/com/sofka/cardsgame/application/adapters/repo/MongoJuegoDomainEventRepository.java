package com.sofka.cardsgame.application.adapters.repo;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.cardgames.business.gateway.JuegoDomainEventRepository;
import com.sofka.cardsgame.application.generic.EventStoreRepository;

@Component
public class MongoJuegoDomainEventRepository implements JuegoDomainEventRepository {
    private final EventStoreRepository repository;

    public MongoJuegoDomainEventRepository(EventStoreRepository repository) {
        this.repository = repository;
    }


    @Override
    public Flux<DomainEvent> obtenerEventosPor(String id) {
        return repository.getEventsBy("game", id);
    }
}