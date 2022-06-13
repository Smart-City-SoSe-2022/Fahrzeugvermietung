package com.example.backend.controller;

import com.example.backend.BillForBank;
import com.example.backend.entity.Contract;
import com.example.backend.entity.Customer;
import com.example.backend.entity.Vehicle;
import com.example.backend.repository.ContractRepository;
import com.example.backend.repository.CustomerRepository;
import com.example.backend.repository.VehicleRepository;
import com.example.backend.service.PaymentStatus;
import com.example.backend.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fahrzeugvermietung/fahrzeug/{id}")
public class ContractController {

    private ContractRepository contractRepository;
    private VehicleRepository vehicleRepository;
    @Autowired
    RabbitMQSender rabbitMQSender;
    private Long vehicleID;
    private Long customerID;

    public ContractController(VehicleRepository vehicleRepository,
                              ContractRepository contractRepository){
        this.vehicleRepository = vehicleRepository;
        this.contractRepository = contractRepository;
    }

    @GetMapping("")
    public Vehicle getVehicle(@PathVariable("id") Long id){
        this.vehicleID=id;
        this.customerID=1L;
        return vehicleRepository.getById(id);
    }

    @PostMapping("")
    public void createRentContract(@RequestParam("priceOption") int priceOption){
        contractRepository.saveAndFlush(new Contract(customerID,vehicleID,priceOption));
        rabbitMQSender.send(new BillForBank(customerID,priceOption));
    }

}
