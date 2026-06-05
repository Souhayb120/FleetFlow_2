package com.example.FleetFlow.repositories;

import com.example.FleetFlow.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
}
