package com.example.mutuellesante.security.entity;


import com.example.mutuellesante.entity.Rolename;
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
    protected Rolename roleName;


    //One to Many avec UserEntity
    @OneToMany (mappedBy = "role")
    private List<UserEntity> users;
}
