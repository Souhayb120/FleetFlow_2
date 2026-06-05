package com.example.FleetFlow.repositories;

import com.example.FleetFlow.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
