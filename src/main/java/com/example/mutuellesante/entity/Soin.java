package com.example.mutuellesante.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="soin")
@DiscriminatorValue(value="soin")
@Data
public class Soin {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false, updatable = false)
    protected Integer soin_id;

    @Column(nullable = false)
    private String nom_soin;

    @Column(nullable = false)
    private float prix_soin;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

}
