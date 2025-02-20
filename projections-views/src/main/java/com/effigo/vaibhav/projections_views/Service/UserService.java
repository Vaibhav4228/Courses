package com.effigo.vaibhav.projections_views.Service;

import com.effigo.vaibhav.projections_views.DTOs.UserDTO;
import com.effigo.vaibhav.projections_views.Projections.UserProjection;
import com.effigo.vaibhav.projections_views.Repo.UserRepository;
import com.effigo.vaibhav.projections_views.mappers.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Page<UserDTO> getPaginatedUsers(Pageable pageable) {
        return userRepository.findAllBy(pageable)
                .map(userMapper::userProjectionToUserDTO);
    }
    public UserDTO createUser(UserDTO userDTO) {
        var user = userMapper.userDTOToUser(userDTO);
        var savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }
}
