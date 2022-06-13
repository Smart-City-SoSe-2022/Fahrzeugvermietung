package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerID;
    private Long vehicleID;
    private int priceOption;

    public Contract(Long customerID, Long vehicleID, int priceOption){
        this.customerID = customerID;
        this.vehicleID = vehicleID;
        this.priceOption = priceOption;
    }

    public Contract(){

    }
}
