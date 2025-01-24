package com.effigo.vaibhav.crud_app.mappers;

import com.effigo.vaibhav.crud_app.dto.UserDto;
import com.effigo.vaibhav.crud_app.entity.User;

public class UserMapper {
    public static UserDto toDto (User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    public static User toEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return user;
    }

}
