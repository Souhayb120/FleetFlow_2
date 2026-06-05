package com.example.FleetFlow.models;

import com.example.FleetFlow.Enums.Role;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity implements UserDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String userName;
   private String email;
   private String password;
   @Enumerated(EnumType.STRING)
   private Role role;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
   }

   @Override
   public String getUsername() {
      return userName;
   }

}
