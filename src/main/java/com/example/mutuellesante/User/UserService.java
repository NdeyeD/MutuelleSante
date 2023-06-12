package com.example.mutuellesante.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity addUser(UserEntity user){
        return userRepository.save(user);
    }

    public List<UserEntity> getAllUser(){
        return userRepository.findAll();
    }

    public UserEntity findUserById(Integer userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " does not exist."));
    }

    public UserEntity updateUser(UserEntity user){
        if (userRepository.existsById(user.getUser_id())) {
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User with ID " + user.getUser_id() + " does not exist.");
        }
    }

    public void deleteUser(Integer userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }
    }
}
