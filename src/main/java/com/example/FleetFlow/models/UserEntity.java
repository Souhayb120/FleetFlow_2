package com.example.FleetFlow.models;

import com.example.FleetFlow.Enums.Role;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String userName;
   private String email;
   private String password;
   @Enumerated(EnumType.STRING)
   private Role role;
}
