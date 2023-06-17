package com.example.mutuellesante.controller;

import com.example.mutuellesante.entity.Rolename;
import com.example.mutuellesante.security.entity.Role;
import com.example.mutuellesante.security.entity.UserEntity;
import com.example.mutuellesante.security.repository.RoleRepository;
import com.example.mutuellesante.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/utilisateurs")
    public String afficherUtilisateur(
            Model model,
            @RequestParam("id") Integer id
    ){
        UserEntity user = userService.findUserById(id);
        if(user.getRole().getRoleName()== Rolename.admin){

            UserEntity userEntity = userService.findUserById(id.intValue());//
            model.addAttribute("user", userEntity);

            List<Role> roles = roleRepository.findAll() ;
            model.addAttribute("listeroles",roles);
            return "utilisateurs";
        }
        return "redirect:/home?id="+id;
    }

    @PostMapping("/utilisateurs")
    public String ajouterUtilisateur(
            Model model,
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role_id") Integer role_id,
            @RequestParam("id") Integer id
    ){
        UserEntity user = new UserEntity();
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setPassword(password);
        user.setUsername(username);
        user.setEmail(email);

        Optional<Role> role = roleRepository.findById(role_id);

        if(role!=null){

            Role role1 = role.get();
            user.setRole(role1);
            userService.saveUser(user);

            return "redirect:/utilisateurs?id="+id;
        }
        return "redirect:/home?id="+id;
    }
}
