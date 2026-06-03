package com.example.FleetFlow.repositories;

import com.example.FleetFlow.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUserName(String userName);

    boolean existsByEmail(String email);
}