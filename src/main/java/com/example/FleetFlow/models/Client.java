package com.example.FleetFlow.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client extends UserEntity{
    private String nom;
    private int age;
    private String phone;
    @OneToMany(mappedBy = "client")
    private List<Livraison> livraisonList;

    public Client(String ayoub, String mail, String number) {
    }


}
