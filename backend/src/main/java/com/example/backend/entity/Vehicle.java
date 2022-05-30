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
    private long id;
    private String type;
    private String modell;
    private String color;
    private int distance;
    private int maxSpeed;
    private int mileage;
    private int priceDay;
    private int priceWeek;
    private int priceMonth;
}
