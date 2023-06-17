package com.example.mutuellesante.controller;

import com.example.mutuellesante.entity.Rolename;
import com.example.mutuellesante.security.entity.UserEntity;
import com.example.mutuellesante.security.service.UserService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MutuelleController {
    @Autowired
    private UserService userService;

    @GetMapping("/add-mutuelle")
    public String vueMutuelle(Model model,
            @RequestParam("id") int id
    ){
        UserEntity user = userService.findUserById(id);
        if(user.getRole().getRoleName()== Rolename.admin){
            return "mutuelle";
        }
        return "redirect:/home?id="+id;
    }
}
