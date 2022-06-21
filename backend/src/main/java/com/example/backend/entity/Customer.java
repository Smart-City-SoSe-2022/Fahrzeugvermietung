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
public class Customer{
    @Id
    private Long id;

    public Customer(Long id){
        this.id=id;
    }

    public Customer() {

    }
}
