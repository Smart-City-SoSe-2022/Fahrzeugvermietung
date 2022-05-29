package com.example.backend.service;

import com.example.backend.Rent;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("microservice.eventbus")
    private String exchange;

    @Value("anonymous.info")
    private String routingKey;

    public void send(Rent rent){
        rabbitTemplate.convertAndSend(exchange,routingKey,rent);
        System.out.println("Send msg = " + rent);
    }

}
