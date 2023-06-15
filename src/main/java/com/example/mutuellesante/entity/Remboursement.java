package com.example.mutuellesante.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name="remboursement")
@DiscriminatorValue(value="remboursement")
@Data
public class Remboursement {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false, updatable = false)
    private Integer rembousements_id;

    @Column(nullable = false)
    private String identifiants;

    @Column(nullable = false)
    private float montant;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private status status;


    //Many To One avec Medecin
    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    //Many To One avec mutuelle
    @ManyToOne
    @JoinColumn(name = "mutuelle_id")
    private Mutuelle mutuelle;
}
