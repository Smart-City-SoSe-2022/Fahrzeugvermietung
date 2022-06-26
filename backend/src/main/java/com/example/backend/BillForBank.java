package com.example.backend;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BillForBank {
    private Long customer_id=1L;
    private String payment_receiver="Smart Rent";
    private String payment_bank="DE348992xxxx00068x";
    private int payment_price;

    public BillForBank(Long customer_id, int payment_price) {
        this.customer_id = customer_id;
        this.payment_price = payment_price;
    }

    @Override
    public String toString() {
        return "Rechnung [mieterID=" + customer_id +  ", Empfänger=" + payment_receiver
                + ", Bankleitzahl=" + payment_bank + ", Preis=" + payment_price
                + "]";
    }
}

