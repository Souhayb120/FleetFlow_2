package com.example.FleetFlow.DTO;

import com.example.FleetFlow.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAuthRequest {
    private String userName;
    private String email;
    private String password;
    private Role role;
}
