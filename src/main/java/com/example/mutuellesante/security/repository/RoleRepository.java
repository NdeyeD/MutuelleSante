package com.example.mutuellesante.security.repository;

import com.example.mutuellesante.entity.Rolename;
import com.example.mutuellesante.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role,Integer> {
        Role findByRoleName(Rolename roleName);

}


