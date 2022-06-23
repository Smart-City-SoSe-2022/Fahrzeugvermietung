package com.example.backend.service;

import com.example.backend.entity.Customer;

import com.example.backend.repository.CustomerRepository;
import net.minidev.json.*;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PortalNewAccount implements MessageListener {
    private CustomerRepository customerRepository;

    public PortalNewAccount(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "",durable = ""),
            exchange = @Exchange(value = "microservice.eventbus",type = "topic",durable = ""),key = "portal.account.created"
    )
    )
    @Override
    public void onMessage(Message message) {
        String data = new String(message.getBody());
        JSONParser parser = new JSONParser();
        JSONObject neu;
        try {
            neu = (JSONObject) parser.parse(data);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        customerRepository.saveAndFlush(new Customer(neu.getAsNumber("id").longValue()));
    }
}
