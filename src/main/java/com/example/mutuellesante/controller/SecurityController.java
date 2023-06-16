package com.example.mutuellesante.controller;

import com.example.mutuellesante.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SecurityController {
    @Autowired
    UserServiceImpl service;

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails userDetails){
        return "redirect:/connexion";
    }

    @GetMapping("/403")
    public String error403(){
        return "errors/403";
    }
    @GetMapping("/404")
    public String error404(){
        return "errors/404";
    }}