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

    @GetMapping(value ="/gemietete_fahrzeuge/id")
    public String producer(@RequestParam("customer_id") String customer_id,
                           @RequestParam("firstname") String firstname,
                           @RequestParam("lastname") String lastname,
                           @RequestParam("payment_receiver") String payment_receiver,
                           @RequestParam("payment_bank") String payment_bank,
                           @RequestParam("payment_price") String payment_price
                           ){
        Bill bill = new Bill();
        bill.setCustomer_id(customer_id);
        bill.setFirstname(firstname);
        bill.setLastname(lastname);
        bill.setPayment_receiver(payment_receiver);
        bill.setPayment_bank(payment_bank);
        bill.setPayment_price(payment_price);
        rabbitMQSender.send(bill);

        return "Message sent succesfully";
    }

}
