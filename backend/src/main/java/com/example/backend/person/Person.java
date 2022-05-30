package com.example.backend.person;

import com.example.backend.address.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Person {
    private String firstname;
    private String lastname;
    private String salution;
    private String birthdate;
    private int tel;
    private Address address;

    public Person(String firstname, String lastname, String salution, String birthdate, int tel, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.salution = salution;
        this.birthdate = birthdate;
        this.tel = tel;
        this.address = address;
    }
}
