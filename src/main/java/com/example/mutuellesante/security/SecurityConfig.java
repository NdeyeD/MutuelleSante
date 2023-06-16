package com.example.mutuellesante.security;

import com.example.mutuellesante.security.entity.UserEntity;
import com.example.mutuellesante.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsService service;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(service);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeRequests()
                .requestMatchers("/webjars/**", "/static/css/**", "/static/js/**", "/static/assets/img/**").permitAll()
                .requestMatchers("/ac/**").hasAuthority("pharmacien")
                .anyRequest()
                .authenticated()
                .and()

                .formLogin(form -> form
                        .loginPage("/connexion")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            // Récupérer l'ID de l'utilisateur
                            String username = authentication.getName();
                            UserEntity user = userService.getUserByEmail(username);
                            int id = user.getUser_id();

                            // Rediriger vers la page d'accueil avec l'ID en tant que paramètre
                            response.sendRedirect("/home?id=" + id);
                        })
                        .failureUrl("/connexion?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/deconnexion")
                        .logoutSuccessUrl("/connexion")
                        .permitAll()
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedPage("/403")
                );

        return http.build();
    }

    // Autres configurations et beans

}
