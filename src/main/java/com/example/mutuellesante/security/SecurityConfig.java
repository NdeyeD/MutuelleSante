package com.example.mutuellesante.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
public class SecurityConfig {


    @Autowired
    UserDetailsService service;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=
                new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(service);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http.formLogin();
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests()
                .requestMatchers("/webjars/**").permitAll()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/ac/**").hasAuthority("ac")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
        http.exceptionHandling().accessDeniedPage("/403");
        return http.build();
    }




}
