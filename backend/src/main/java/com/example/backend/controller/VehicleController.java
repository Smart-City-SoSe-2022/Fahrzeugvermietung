package com.example.backend.controller;

import com.example.backend.entity.Vehicle;
import com.example.backend.jwt.JwtTokenUtil;
import com.example.backend.repository.EndUserRepository;
import com.example.backend.repository.VehicleRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fahrzeugvermietung")
public class VehicleController {
    private VehicleRepository vehicleRepository;
    private JwtTokenUtil jwtTokenUtil;

    public VehicleController(VehicleRepository vehicleRepository, EndUserRepository endUserRepository) {
        this.vehicleRepository = vehicleRepository;
        jwtTokenUtil = new JwtTokenUtil(endUserRepository);
    }

    @GetMapping("")
    public List<Vehicle> index(@CookieValue(name = "JWT") String jwtID){
        if(jwtTokenUtil.checkLoggedIn(jwtID)){
            List<Vehicle> list = vehicleRepository.findAll();
            for(int i=0;i<list.size();i++){
                if(!list.get(i).isAvailability()){
                    list.remove(i);
                    i--;
                }
            }
            return list;
        }else {
         return new ArrayList<Vehicle>();
        }
    }

    @GetMapping("/user")
    public boolean isLessor(){
        return jwtTokenUtil.checkUserLessor();
    }

    @GetMapping("/vermietete_fahrzeuge")
    public List<Vehicle> getContractsAllRentVehicles(@CookieValue(name = "JWT") String jwtID){
        if(jwtTokenUtil.checkLoggedIn(jwtID) && jwtTokenUtil.checkUserLessor()) {
            List<Vehicle> list = vehicleRepository.findAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isAvailability()) {
                    list.remove(i);
                    i--;
                }
            }
            return list;
        } else {
            return new ArrayList<Vehicle>();
        }
    }

    @PostMapping("/neues_fahrzeug")
    public void createVehicle(@CookieValue("JWT") String jwtID,
            @RequestBody Vehicle vehicle){
        if(jwtTokenUtil.checkLoggedIn(jwtID) && jwtTokenUtil.checkUserLessor()){
            vehicleRepository.saveAndFlush(new Vehicle(vehicle.getType(),
                    vehicle.getModell(),
                    vehicle.getColor(),
                    vehicle.getDistance(),
                    vehicle.getMaxSpeed(),
                    vehicle.getMileage(),
                    vehicle.getPriceDay(),
                    vehicle.getPriceWeek(),
                    vehicle.getPriceMonth(),
                    vehicle.isAvailability()));
        }
    }
}
