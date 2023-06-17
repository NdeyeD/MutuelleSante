package com.example.mutuellesante.controller;


import com.example.mutuellesante.entity.Rolename;
import com.example.mutuellesante.security.entity.UserEntity;
import com.example.mutuellesante.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MedecinController {

    @Autowired
    private UserService userService;
    @GetMapping("/remboursement")
    public String faireuneDemande(
            Model model,
            @RequestParam("id") int id
    ){

        UserEntity user = userService.findUserById(id);
        model.addAttribute("user",user);
        /*

        if(user.getRole().getRoleName()== Rolename.pharmacien || user.getRole().getRoleName()== Rolename.infirmier){
            return "demande";
        }
        */
        return "remboursement";
    }
}
