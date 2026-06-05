package com.example.FleetFlow.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "chauffeur")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Chauffeur   extends UserEntity{
    private String nom;
    private String phone;
    private String permisType;
    private Boolean isDisponible = true;

    @OneToMany(mappedBy = "chauffeur")
    private List<Livraison> livraisonList;

    @OneToMany(mappedBy = "chauffeur")
    @JsonManagedReference
    private List<Vehicule> vichelList;



    public String sayHi(String name){
        return name;
    }

    public String sayHi(String name , String firstName){
        return name + firstName;
    }

}
