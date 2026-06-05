package com.example.FleetFlow.Config;


import com.example.FleetFlow.DTO.AuthResponse;
import com.example.FleetFlow.DTO.UserAuthRequest;
import com.example.FleetFlow.models.*;
import com.example.FleetFlow.repositories.AdminRepository;
import com.example.FleetFlow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired  private  AdminRepository adminRepository;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserService userDetailsService;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public void register(UserAuthRequest request) {
        Manager manager = new Manager();
        Admin admin = new Admin();
        Chauffeur chauffeur = new Chauffeur();
        if(request.getRole().name().equals("CHAUFFEUR")){
            chauffeur.setUserName(request.getUserName());
            chauffeur.setNom(request.getNom());
            chauffeur.setPermisType(request.getPermisType());
            chauffeur.setIsDisponible(request.getIsDisponible());
            chauffeur.setEmail(request.getEmail());
            chauffeur.setPassword(passwordEncoder.encode(request.getPassword()));
            chauffeur.setRole(request.getRole());
            userRepository.save(chauffeur);

        }else if(request.getRole().name().equals("ADMIN")){
            admin.setUserName(request.getUserName());
            admin.setEmail(request.getEmail());
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
            admin.setRole(request.getRole());
            userRepository.save(admin);

        }else if(request.getRole().name().equals("MANAGER")){
            manager.setUserName(request.getUserName());
            manager.setEmail(request.getEmail());
            manager.setPassword(passwordEncoder.encode(request.getPassword()));
            manager.setRole(request.getRole());
            userRepository.save(manager);
        }


    }




    public AuthResponse login(UserAuthRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );
        } catch (Exception e) {
            System.out.println("Auth failed: " + e.getMessage()); // 👈 tells you exactly why
            throw e;
        }        System.out.println("Before finding the user");
        UserDetails user = userDetailsService.loadUserByUsername(request.getUserName());
        System.out.println(user.getUsername() + " HERE!!");
        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }


}
