package com.example.mutuellesante.security.service;

import com.example.mutuellesante.security.entity.Role;
import com.example.mutuellesante.security.entity.UserEntity;
import com.example.mutuellesante.security.repository.RoleRepository;
import com.example.mutuellesante.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceImpl, UserDetailsService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(RoleRepository appRoleRepositories, UserRepository appUserRepositories, PasswordEncoder passwordEncoder) {
        this.roleRepository = appRoleRepositories;
        this.userRepository = appUserRepositories;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserEntity getUserByEmail(String username) {
        return userRepository.findByEmail(username);
    }



    @Override
    public UserEntity saveUser(UserEntity user) {
        UserEntity utilisateur = userRepository.findByEmail(user.getEmail());
        if(utilisateur !=null) throw  new RuntimeException("Cet utilisateur existe deja ");
        utilisateur = new UserEntity();
        utilisateur.setUsername(user.getUsername());
        utilisateur.setPassword(passwordEncoder.encode(user.getPassword()));
        utilisateur.setEmail(user.getEmail());
        utilisateur.setRole(user.getRole());
        return userRepository.save(utilisateur);
    }

    public UserEntity addUser(UserEntity user){
        return userRepository.save(user);
    }

    public List<UserEntity> getAllUser(){
        return userRepository.findAll();
    }

    public UserEntity findUserById(Integer userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " does not exist."));
    }

    public UserEntity updateUser(UserEntity user){
        if (userRepository.existsById(user.getUser_id())) {
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User with ID " + user.getUser_id() + " does not exist.");
        }
    }

    public void deleteUser(Integer userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }
    }


    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public Role getRoleByRolename(String role) {
        return roleRepository.findByRoleName(role);
    }
    @Override
    public void addRoleToUser(UserEntity user,Role role) {
        UserEntity utilisateur = userRepository.findByEmail(user.getEmail());
        if(utilisateur ==null) throw  new RuntimeException("Cet utilisateur n'existe pas ");

        Role roole =roleRepository.findByRoleName(role.getRoleName());
        if(roole==null) throw  new RuntimeException("Cet Role n'existe pas ");
        utilisateur.setRole(roole);
        userRepository.save(utilisateur);
    }


    @Override
    public void removeRoleToUser(UserEntity user,Role role) {
        UserEntity utilisateur =userRepository.findByEmail(user.getEmail());
        if(utilisateur ==null) throw  new RuntimeException("Cet utilisateur n'existe pas ");
        Role roole =roleRepository.findByRoleName(role.getRoleName());
        if(roole==null) throw  new RuntimeException("Cet Role n'existe pas ");
        roole.setRoleName("");
        utilisateur.setRole(roole);
        userRepository.save(utilisateur);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = getUserByEmail(username);
        if(user ==null) throw  new RuntimeException("Cet utilisateur n'existe pas ");

        return new User(user.getEmail(),user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole().getRoleName())));

    }
}
