package com.example.mutuellesante.entity;

import com.example.mutuellesante.security.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="patient")
@DiscriminatorValue(value="patient")
@Data
@Setter
@Getter
public class Patient extends UserEntity {

    @Column(nullable = false, unique = true)
    private Long numero_carte;

}
