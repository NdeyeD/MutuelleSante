package com.example.mutuellesante;

import com.example.mutuellesante.entity.Patient;
import com.example.mutuellesante.entity.Rolename;
import com.example.mutuellesante.security.entity.Role;
import com.example.mutuellesante.security.entity.UserEntity;
import com.example.mutuellesante.security.repository.RoleRepository;
import com.example.mutuellesante.security.repository.UserRepository;
import com.example.mutuellesante.security.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
public class MutuelleSanteApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(MutuelleSanteApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {



        //Creer un Admin
        UserEntity user =  new UserEntity();
        user.setUsername("Admin 1");
        user.setNom("Thiam");
        user.setPrenom("El Hadji Daouda");
        user.setEmail("admin1@gmail.com");
        user.setPassword("passer123");

            //Creer le role et affecter
        Role rolepatient = new Role();
        rolepatient.setRoleName(Rolename.admin);

        userService.saveRole(rolepatient);

        user.setRole(rolepatient);

        userService.saveUser(user);




    }
}
