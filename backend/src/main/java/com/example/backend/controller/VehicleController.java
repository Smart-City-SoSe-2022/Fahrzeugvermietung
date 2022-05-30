package com.example.backend.controller;

import com.example.backend.entity.Vehicle;
import com.example.backend.repository.VehicleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fahrzeugvermietung")
public class VehicleController {
    private VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("")
    public List<Vehicle> index(){
        return vehicleRepository.findAll();
    }
}
