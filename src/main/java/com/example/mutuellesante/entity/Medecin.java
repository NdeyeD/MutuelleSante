package com.example.mutuellesante.entity;

import com.example.mutuellesante.security.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="medecin")
@DiscriminatorValue(value="Medecin")
@Data
@Setter
@Getter
public class Medecin extends UserEntity {

    @Column(nullable = false)
    private String structure_sante;

    //One to Many avec Remboursement
    @OneToMany(mappedBy = "medecin")
    private List<Remboursement> remboursements;
}
