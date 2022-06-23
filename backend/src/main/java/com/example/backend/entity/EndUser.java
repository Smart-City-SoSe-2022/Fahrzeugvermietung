package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class EndUser {
    @Id
    private Long id;
    private boolean lessor;

    public EndUser(Long id, boolean lessor){
        this.id=id;
        this.lessor = lessor;
    }

    public EndUser() {

    }
}
