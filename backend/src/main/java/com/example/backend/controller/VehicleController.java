package com.example.backend.controller;

import com.example.backend.entity.Vehicle;
import com.example.backend.repository.VehicleRepository;
import org.springframework.web.bind.annotation.*;

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
        List<Vehicle> list = vehicleRepository.findAll();
        for(int i=0;i<list.size();i++){
            if(!list.get(i).isAvailability()){
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    @GetMapping("/vermietete_fahrzeuge")
    public List<Vehicle> getContractsAllRentVehicles(){
        List<Vehicle> list = vehicleRepository.findAll();
        for(int i=0;i<list.size();i++){
            if(list.get(i).isAvailability()){
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    @GetMapping("/fahrzeug/{id}")
    public Vehicle getVehicle(@PathVariable("id") Long id){
        return vehicleRepository.getById(id);
    }

    @PostMapping("/neues_fahrzeug")
    public void createVehicle(@RequestParam("type") String type,
                              @RequestParam("modell") String modell,
                              @RequestParam("color") String color,
                              @RequestParam("distance") int distance,
                              @RequestParam("max_speed") int max_speed,
                              @RequestParam("mileage") int mileage,
                              @RequestParam("price_day") int price_day,
                              @RequestParam("price_week") int price_week,
                              @RequestParam("price_month") int price_month,
                              @RequestParam("availability") boolean availability){
        vehicleRepository.saveAndFlush(new Vehicle(type,modell,color,distance,max_speed,mileage,price_day,price_week,price_month,availability));
    }


}
