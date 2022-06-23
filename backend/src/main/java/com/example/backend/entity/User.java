package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class User {
    @Id
    private Long id;
    private boolean lessor;

    public User(Long id){
        this.id=id;
    }

    public User() {

    }
}
