package com.example.backend.service;

import com.example.backend.entity.User;

import com.example.backend.repository.UserRepository;
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

@Component
public class PortalNewAccount implements MessageListener {
    private UserRepository userRepository;

    public PortalNewAccount(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "Fahrzeugvermietung",durable = ""),
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
        userRepository.saveAndFlush(new User(neu.getAsNumber("id").longValue()));
    }
}
