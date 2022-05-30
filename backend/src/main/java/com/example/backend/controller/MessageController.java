package com.example.backend.controller;

import com.example.backend.Bill;
import com.example.backend.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/fahrzeugvermietung/")
public class MessageController {

    @Autowired
    RabbitMQSender rabbitMQSender;

    @GetMapping(value ="/producer")
    public String producer(@RequestParam("billCustomer") String billCustomer, @RequestParam("billID") String billID){
        Bill bill = new Bill();
        bill.setBillID(billID);
        bill.setBillCustomer(billCustomer);
        rabbitMQSender.send(bill);

        return "Message sent succesfully";
    }

}
