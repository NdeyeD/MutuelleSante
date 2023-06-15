package com.example.mutuellesante.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_entity")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false, updatable = false)
    protected Integer user_id;
    @Column(unique = true, nullable = false)
    protected String email;
    @Column(unique = true, nullable = false)
    protected String username;
    @Column(nullable = false)
    protected String nom;
    @Column(nullable = false)
    protected String prenom;
    @Column(nullable = false)
    protected String password;

    //One To One avec Role
    @ManyToOne
    @JoinColumn(name = "role_id")
    protected Role role;

}