package com.example.backend.controller;

import com.example.backend.BillForBank;
import com.example.backend.ContractData;
import com.example.backend.entity.Contract;
import com.example.backend.entity.Vehicle;
import com.example.backend.jwt.JwtTokenUtil;
import com.example.backend.repository.ContractRepository;
import com.example.backend.repository.EndUserRepository;
import com.example.backend.repository.VehicleRepository;
import com.example.backend.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fahrzeugvermietung/fahrzeug/{id}")
public class SingleVehicleController {
    private ContractRepository contractRepository;
    private VehicleRepository vehicleRepository;
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    RabbitMQSender rabbitMQSender;
    private Long vehicleID;
    private Long userID;

    public SingleVehicleController(VehicleRepository vehicleRepository,
                                   ContractRepository contractRepository,
                                   EndUserRepository endUserRepository){
        this.vehicleRepository = vehicleRepository;
        this.contractRepository = contractRepository;
        jwtTokenUtil = new JwtTokenUtil(endUserRepository);
    }

    @GetMapping("")
    @CrossOrigin
    public Vehicle getVehicle(@CookieValue("JWT") String jwtID,
            @PathVariable("id") Long id){
        if(jwtTokenUtil.checkLoggedIn(jwtID)){
            this.vehicleID=id;
            this.userID=jwtTokenUtil.getMyID(jwtID);
            return vehicleRepository.getById(id);
        }else {
            return new Vehicle();
        }
    }

    @PostMapping("/createContract")
    public void createRentContract(@CookieValue("JWT") String jwtID,
            @RequestBody ContractData contractData){
        if(jwtTokenUtil.checkLoggedIn(jwtID)){
            contractRepository.saveAndFlush(new Contract(userID,vehicleID,contractData.getPriceOption(),contractData.getReturnDate()));
            Vehicle vehicle = vehicleRepository.findById(vehicleID).get();
            vehicle.setAvailability(false);
            vehicle.setUserID(userID);
            vehicleRepository.save(vehicle);
            rabbitMQSender.send(new BillForBank(userID,contractData.getPriceOption()));
        }
    }
}
