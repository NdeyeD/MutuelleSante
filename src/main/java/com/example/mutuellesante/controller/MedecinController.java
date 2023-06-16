package com.example.mutuellesante.controller;

import ch.qos.logback.core.model.Model;
import com.example.mutuellesante.entity.Rolename;
import com.example.mutuellesante.security.entity.UserEntity;
import com.example.mutuellesante.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MedecinController {

    @Autowired
    private UserService userService;
    @GetMapping("/remboursement")
    public String faireuneDemande(Model model,
        @RequestParam("id") int id
    ){
        /*
        UserEntity user = userService.findUserById(id);
        if(user.getRole().getRoleName()== Rolename.pharmacien || user.getRole().getRoleName()== Rolename.infirmier){
            return "demande";
        }
        */
        return "redirect/home?id="+id;
    }
}
