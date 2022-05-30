package com.example.backend.address;

import lombok.Getter;

@Getter
public class Address {
    private String address;
    private String city;
    private int zipcode;

    public Address(String address, String city, int zipcode) {
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
    }
}
