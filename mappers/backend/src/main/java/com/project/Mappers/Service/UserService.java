package com.project.Mappers.Service;

import com.project.Mappers.Entity.UserEntity;
import com.project.Mappers.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntity signUp(String username, String rawPassword) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        String hashedPassword = bCryptPasswordEncoder.encode(rawPassword);

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }


    public UserEntity login(String username, String rawPassword) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!bCryptPasswordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
