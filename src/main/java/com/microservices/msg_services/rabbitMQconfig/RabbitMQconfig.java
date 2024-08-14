package com.microservices.msg_services.rabbitMQconfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQconfig {

    @Bean
    public Queue ordersQueue() {
        return new Queue("orders", true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    public Binding binding(Queue ordersQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(ordersQueue).to(fanoutExchange);
    }
}
