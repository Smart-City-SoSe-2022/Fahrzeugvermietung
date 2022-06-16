package com.example.backend.service;

import com.example.backend.BillForBank;
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

    @Value("bank.payment.received")
    private String routingKey;

    public void send(BillForBank bill){
        rabbitTemplate.convertAndSend(exchange,routingKey,bill);
        System.out.println("Send msg = " + bill);
    }

}
