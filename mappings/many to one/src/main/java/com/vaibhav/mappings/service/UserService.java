package com.vaibhav.mappings.service;

import com.vaibhav.mappings.dto.UserDTO;
import com.vaibhav.mappings.entity.User;
import com.vaibhav.mappings.mapper.UserMapper;
import com.vaibhav.mappings.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserService {
    private  UserRepository userRepository;
    private  UserMapper userMapper;

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }




}

