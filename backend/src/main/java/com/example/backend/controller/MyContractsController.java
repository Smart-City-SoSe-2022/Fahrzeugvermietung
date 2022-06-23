package com.example.backend.controller;

import com.example.backend.entity.Contract;
import com.example.backend.entity.Vehicle;
import com.example.backend.repository.ContractRepository;
import com.example.backend.repository.VehicleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/fahrzeugvermietung/gemietete_fahrzeuge")
public class MyContractsController {
    private ContractRepository contractRepository;
    private VehicleRepository vehicleRepository;
    private Long myUserID=1L;

    public MyContractsController(ContractRepository contractRepository,
                                VehicleRepository vehicleRepository) {
        this.contractRepository = contractRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("")
    public List<Optional> index(){
        List<Contract> list = contractRepository.findAll();
        List<Optional> vehicleList = new ArrayList<>() {
        };
        for(int i=0;i<list.size();i++){
            if(!Objects.equals(list.get(i).getUserID(), myUserID)){
                list.remove(i);
                i--;
            }else{
                vehicleList.add(vehicleRepository.findById(list.get(i).getVehicleID()));
            }
        }
        return vehicleList;
    }
}
