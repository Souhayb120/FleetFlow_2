package com.example.FleetFlow.services.Impl;

import com.example.FleetFlow.DTO.UserAuthRequest;
import com.example.FleetFlow.models.UserEntity;
import com.example.FleetFlow.repositories.UserRepository;
import com.example.FleetFlow.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity register(UserAuthRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());


        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity newData) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setUserName(newData.getUsername());
        user.setEmail(newData.getEmail());
        user.setRole(newData.getRole());

        return userRepository.save(user);
    }

    @Override
    public Page<UserEntity> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    @Override
    public UserEntity findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}