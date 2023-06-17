package com.example.mutuellesante.security.service;

import com.example.mutuellesante.entity.Rolename;
import com.example.mutuellesante.security.entity.Role;
import com.example.mutuellesante.security.entity.UserEntity;

public interface UserServiceImpl {

    UserEntity getUserByEmail(String users);

    UserEntity saveUser(UserEntity users);

    Role saveRole(Role role);

    Role getRoleByRolename(Rolename rolename);

    void addRoleToUser(UserEntity user,Role role);

    void removeRoleToUser(UserEntity user,Role role);
}
