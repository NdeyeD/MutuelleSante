package com.example.mutuellesante.Repository;
import com.example.mutuellesante.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {}
