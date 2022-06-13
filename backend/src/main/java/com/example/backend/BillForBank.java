package com.example.backend;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BillForBank {
    private String customer_id;
    private String firstname;
    private String lastname;
    private String payment_receiver;
    private String payment_bank;
    private String payment_price;

    @Override
    public String toString() {
        return "Rechnung [mieterID=" + customer_id + ", mieterVorname=" + firstname
                + ", mieterNachname=" + lastname + ", Empfänger=" + payment_receiver
                + ", Bankleitzahl=" + payment_bank + ", Preis=" + payment_price
                + "]";
    }
}

