package com.effigo.vaibhav.projections_views.mappers;

import com.effigo.vaibhav.projections_views.DTOs.UserDTO;
import com.effigo.vaibhav.projections_views.Entity.User;
import com.effigo.vaibhav.projections_views.Projections.UserProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    UserDTO userProjectionToUserDTO(UserProjection userProjection);
}
