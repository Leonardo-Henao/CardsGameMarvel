package com.sofka.cardgames.business.gateway;

import com.sofka.cardgames.business.gateway.model.CartaMaestra;
import reactor.core.publisher.Flux;

public interface ConsultaCartasMaestrasService {

    Flux<CartaMaestra> consultarTodasLasCartas();
}
