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
    private String returnDate;

    public Contract(Long customerID, Long vehicleID, int priceOption, String returnDate){
        this.customerID = customerID;
        this.vehicleID = vehicleID;
        this.priceOption = priceOption;
        this.returnDate = returnDate;
    }

    public Contract(){

    }
}
