package com.example.mutuellesante.controller;


import org.springframework.ui.Model;
import com.example.mutuellesante.security.entity.UserEntity;
import com.example.mutuellesante.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/connexion")
    public String login() {
        return "login";
    }

    @PostMapping("/connexion")
    public String processLogin(
            Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        // Vérifier les informations d'identification de l'utilisateur
        UserEntity user = userService.getUserByEmail(username);

        // Si les informations d'identification sont valides, rediriger vers la page d'accueil avec l'ID en tant que paramètre
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            int id = user.getUser_id();
            redirectAttributes.addFlashAttribute("id", id);
            return "redirect:/home";
        }

        // Si les informations d'identification sont invalides, rediriger vers la page de connexion avec un message d'erreur
        return "redirect:/connexion?error=true";
    }


    @GetMapping("/home")
    public String afficherPageHome(Model model, @RequestParam("id") Integer id) {

        if (id != null) {
            UserEntity userEntity = userService.findUserById(id.intValue());//
            model.addAttribute("user", userEntity);
        } else {
            // Gérer le cas où la clé "id" est absente ou a une valeur nulle
            // Par exemple, rediriger vers une page d'erreur ou afficher un message d'erreur
            return "errors/404";
        }
        return "home";
    }




    @PostMapping("/deconnexion")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Supprimer le cookie de session en invalidant la session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();

            // Supprimer le cookie de session en le rendant invalide
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        break;
                    }
                }
            }
        }
        response.setStatus(HttpServletResponse.SC_FOUND);
        response.setHeader("Location", "/connexion?logout=true");
        response.setHeader("Connection", "close");
        return "redirect:/connexion?logout=true";
    }
}
