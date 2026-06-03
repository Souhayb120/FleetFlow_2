package com.example.FleetFlow.services;

import com.example.FleetFlow.DTO.UserAuthRequest;
import com.example.FleetFlow.models.UserEntity;
import com.example.FleetFlow.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserEntity register(UserAuthRequest request);

    void deleteUser(Long id);

    UserEntity updateUser(Long id, UserEntity user);

    Page<UserEntity> getAllUsers(Pageable pageable);

    UserEntity findByEmail(String email);

    UserEntity findByUserName(String userName);
}