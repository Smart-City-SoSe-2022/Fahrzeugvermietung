package com.example.backend;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Bill.class)
public class Bill {
    private String billID;
    private String billCustomer;

    @Override
    public String toString() {
        return "Mietantrag [mieterName=" + billCustomer + ", mieterID=" + billID + "]";
    }
}
