package com.example.FleetFlow.DTO;

import com.example.FleetFlow.Enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAuthRequest {
    @NotNull(message = "Username is mandatory")
    private String userName;

    private String nom;
    private String permisType;
    private Boolean isDisponible;
    @NotNull(message = "Email is mandatory")
    @Email
    private String email;
    @NotNull(message = "Role is mandatory")
    private Role role;
    @NotNull(message = "Password is mandatory")
    private String password;
}
