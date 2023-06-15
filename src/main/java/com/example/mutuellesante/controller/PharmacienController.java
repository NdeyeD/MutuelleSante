package com.example.mutuellesante.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PharmacienController {

    @GetMapping("/pharmacien/remboursement")
    public String faireuneDemande(Model model){

        return "demande";
    }
}
