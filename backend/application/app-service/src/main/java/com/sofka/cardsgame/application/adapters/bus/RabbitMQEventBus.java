package com.sofka.cardsgame.application.adapters.bus;

import co.com.sofka.domain.generic.DomainEvent;

import com.sofka.cardsgame.application.ApplicationConfig;
import com.sofka.cardsgame.application.GsonEventSerializer;
import com.sofka.cardsgame.application.generic.EventBus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQEventBus implements EventBus {

    private final RabbitTemplate rabbitTemplate;
    private final GsonEventSerializer serializer;

    public RabbitMQEventBus(RabbitTemplate rabbitTemplate,  GsonEventSerializer serializer) {
        this.serializer = serializer;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(DomainEvent event) {
        var notification = new Notification(
                event.getClass().getTypeName(),
                serializer.serialize(event)
        );


        rabbitTemplate.convertAndSend(
                ApplicationConfig.EXCHANGE, event.type, notification.serialize().getBytes()
        );
    }

    @Override
    public void publishError(Throwable errorEvent) {
        var event = new ErrorEvent(errorEvent.getClass().getTypeName(), errorEvent.getMessage());
        var notification = new Notification(
                event.getClass().getTypeName(),
                serializer.serialize(event)
        );
        rabbitTemplate.convertAndSend(ApplicationConfig.EXCHANGE, event.type, notification.serialize().getBytes());
    }


}