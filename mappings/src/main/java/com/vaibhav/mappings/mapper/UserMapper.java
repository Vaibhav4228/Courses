package com.vaibhav.mappings.mapper;

import com.vaibhav.mappings.dto.UserDTO;
import com.vaibhav.mappings.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}

