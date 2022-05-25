package com.example.backend;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Rent.class)
public class Rent {
    private String rentID;
    private String rentEmp;

    private String getRentID(){
        return rentID;
    }

    public String getRentEmp() {
        return rentEmp;
    }

    public void setRentEmp(String rentEmp) {
        this.rentEmp = rentEmp;
    }

    public void setRentID(String rentID) {
        this.rentID = rentID;
    }

    @Override
    public String toString() {
        return "Mietantrag [mieterName=" + rentEmp + ", mieterID=" + rentID + "]";
    }
}
