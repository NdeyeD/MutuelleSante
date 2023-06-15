package com.example.mutuellesante.entity;

import com.example.mutuellesante.security.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="mutuelle")
@DiscriminatorValue(value="Mutuelle")
@Data
@Setter
@Getter
public class Mutuelle extends UserEntity {

    @Column(nullable = false)
    private String nom_assurance;

    //One to Many avec Remboursement
    @OneToMany(mappedBy = "mutuelle")
    private List<Remboursement> remboursements;

}
