package com.microservices.msg_services.rabbitMQ.producer;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    private final FanoutExchange fanoutExchange;

    public Producer(RabbitTemplate rabbitTemplate, FanoutExchange fanoutExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.fanoutExchange = fanoutExchange;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);
    }
}



