package com.example.backend.person;

import com.example.backend.address.Address;

public class Customer extends Person{

    public Customer(String firstname, String lastname, String salution, String birthdate, int tel, Address address) {
        super(firstname, lastname, salution, birthdate, tel, address);
    }
}
