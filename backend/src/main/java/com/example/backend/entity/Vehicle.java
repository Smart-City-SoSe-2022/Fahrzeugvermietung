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
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userID;
    private String type;
    private String modell;
    private String color;
    private int distance;
    private int maxSpeed;
    private int mileage;
    private int priceDay;
    private int priceWeek;
    private int priceMonth;
    private boolean availability;

    public Vehicle(String type, String modell, String color, int distance, int maxSpeed, int mileage, int priceDay, int priceWeek, int priceMonth, boolean availability) {
        this.type = type;
        this.modell = modell;
        this.color = color;
        this.distance = distance;
        this.maxSpeed = maxSpeed;
        this.mileage = mileage;
        this.priceDay = priceDay;
        this.priceWeek = priceWeek;
        this.priceMonth = priceMonth;
        this.availability = availability;
    }

    public Vehicle(){

    }
}
