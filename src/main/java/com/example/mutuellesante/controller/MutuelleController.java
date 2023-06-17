package com.example.mutuellesante.controller;

import com.example.mutuellesante.entity.Mutuelle;
import com.example.mutuellesante.entity.Rolename;
import com.example.mutuellesante.repository.MutuelleRepository;
import com.example.mutuellesante.security.entity.Role;
import com.example.mutuellesante.security.entity.UserEntity;
import com.example.mutuellesante.security.repository.RoleRepository;
import com.example.mutuellesante.security.service.UserService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MutuelleController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MutuelleRepository mutuelleRepository;

    @GetMapping("/mutuelle")
    public String vueMutuelle(Model model,
                              @RequestParam("id") Integer id
    ){
        UserEntity user = userService.findUserById(id);
        model.addAttribute("user",user);

        List<Mutuelle> mutuelles = mutuelleRepository.findAll();

        Role roles = roleRepository.findByRoleName(Rolename.mutuelle);

        model.addAttribute("listeroles",roles);
        model.addAttribute("roleadmin",Rolename.mutuelle);
        model.addAttribute("listemutuelle",mutuelles);

        return "mutuelle";

    }

    @PostMapping("/mutuelle")
    public String ajouterMutuelle(
            Model model,
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("nom_assurance") String nom_assurance,
            @RequestParam("password") String password,
            @RequestParam("role_id") Integer role_id,
            @RequestParam("id") Integer id
    ){
        Mutuelle mutuelle = new Mutuelle();
        mutuelle.setNom_assurance(nom_assurance);
        mutuelle.setNom(nom);
        mutuelle.setPrenom(prenom);
        mutuelle.setUsername(username);
        mutuelle.setEmail(email);
        mutuelle.setPassword(password);

        Optional<Role> role = roleRepository.findById(role_id);

        if(role!=null){
            Role role1 = role.get();
            mutuelle.setRole(role1);
            userService.saveUser(mutuelle);
            return "redirect:/mutuelle?id="+id;
        }

        return "redirect:/home?id="+id;
    }



}
