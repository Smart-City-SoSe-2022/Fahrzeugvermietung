package com.example.backend.repository;

import com.example.backend.entity.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser, Long> {
}
