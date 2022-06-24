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
    public boolean isLessor(@CookieValue(name = "JWT") String jwtID){
        return jwtTokenUtil.checkUserLessor(jwtID);
    }

    @GetMapping("/vermietete_fahrzeuge")
    public List<Vehicle> getContractsAllRentVehicles(@CookieValue(name = "JWT") String jwtID){
        if(jwtTokenUtil.checkUserLessor(jwtID)) {
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
