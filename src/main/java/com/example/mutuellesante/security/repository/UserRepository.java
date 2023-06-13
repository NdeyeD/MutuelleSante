package com.example.mutuellesante.security.repository;
import com.example.mutuellesante.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findByEmail(String adresseEmail);


}
