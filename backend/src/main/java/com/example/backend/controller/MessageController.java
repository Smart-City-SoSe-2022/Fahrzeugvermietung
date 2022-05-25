package com.example.backend.controller;

import com.example.backend.Rent;
import com.example.backend.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/backend-rabbitmq/")
public class MessageController {

    @Autowired
    RabbitMQSender rabbitMQSender;

    @GetMapping(value ="/producer")
    public String producer(@RequestParam("rentEmp") String rentEmp,@RequestParam("rentID") String rentID){
        Rent rent = new Rent();
        rent.setRentID(rentID);
        rent.setRentEmp(rentEmp);
        rabbitMQSender.send(rent);

        return "Message sent succesfully";
    }

}
