package com.example.mutuellesante.security.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Column(nullable = false, unique = true)
    protected String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;
}
