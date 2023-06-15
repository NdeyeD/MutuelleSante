package com.example.mutuellesante.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="medicaments")
@DiscriminatorValue(value="medicaments")
@Data
public class Medicaments {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false, updatable = false)
    protected Integer medicament_id;

    @Column(nullable = false)
    private String nom_medicament;
    @Column(nullable = false)
    private float prix_unitaire;
    @Column(nullable = false)
    private int quantite;


}
