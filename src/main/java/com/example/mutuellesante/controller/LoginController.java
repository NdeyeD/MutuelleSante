package com.example.mutuellesante.controller;


import ch.qos.logback.core.model.Model;
import com.example.mutuellesante.security.entity.UserEntity;
import com.example.mutuellesante.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String processLogin(HttpServletRequest request) {
        // Récupérer les paramètres du formulaire de connexion
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Vérifier les informations d'identification de l'utilisateur
        RedirectAttributes redirectAttributes =  new RedirectAttributesModelMap();
        UserEntity user = userService.getUserByEmail(username);


        // Si les informations d'identification sont valides, rediriger vers la page de tableau de bord
        if (user != null) {

            // Vérifier si le mot de passe correspond
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Mot de passe correct, rediriger vers la page de tableau de bord
                return "redirect:/home";
            }
            redirectAttributes.addFlashAttribute("error", "Mot de passe incorrects");
        }

        redirectAttributes.addFlashAttribute("error", "Identifiants incorrects");


        // Si les informations d'identification sont invalides, rediriger vers la page de connexion avec un message d'erreur
        return "redirect:/login?error=true";
    }

    @GetMapping("/home")
    public String afficherPageHome(Model model){
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Invalider la session de l'utilisateur connecté
        request.getSession().invalidate();

        // Rediriger vers la page de connexion
        return "redirect:/login?logout=true";
    }


}
