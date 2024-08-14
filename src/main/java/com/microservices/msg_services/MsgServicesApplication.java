package com.microservices.msg_services;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableRabbit
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MsgServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsgServicesApplication.class, args);
    }
}
