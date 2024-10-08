package com.microservices.msg_services.rabbitMQ.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = "orders")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}


