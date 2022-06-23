package com.example.backend.controller;

import com.example.backend.entity.EndUser;
import com.example.backend.repository.EndUserRepository;

import java.util.List;

public class EndUserController {
    private EndUserRepository endUserRepository;
    public EndUserController(EndUserRepository endUserRepository) {
        this.endUserRepository = endUserRepository;
    }

    public List<EndUser> index(){
        return endUserRepository.findAll();
    }
}
