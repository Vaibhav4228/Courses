package com.effigo.vaibhav.crud_app.controller;


import com.effigo.vaibhav.crud_app.dto.UserDto;
import com.effigo.vaibhav.crud_app.mappers.UserMapper;
import com.effigo.vaibhav.crud_app.entity.User;
import com.effigo.vaibhav.crud_app.exception.UserNotFoundException;
import com.effigo.vaibhav.crud_app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173/")
public class UserController {

    @Autowired
    UserRepo userRepo;


    @GetMapping("/user")
    List<UserDto> allUsers(){
       List<User> users = userRepo.findAll();
       List<UserDto> userDtos = new ArrayList<>();
       for(User user : users){
           userDtos.add(UserMapper.toDto(user));
       }
       return userDtos;
    }

    @PostMapping("/user")
    UserDto newUser(@RequestBody UserDto newUserDto){
        User user = UserMapper.toEntity(newUserDto);
        return UserMapper.toDto(userRepo.save(user));
    }

    @GetMapping("/user/{id}")
    UserDto UserId(@PathVariable Long id){

        try {
            User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
            return UserMapper.toDto(user);
        } catch (UserNotFoundException error) {
            throw error;
        } catch (Exception error) {
            throw new RuntimeException( error);
        }
    }

    @PutMapping("/user/{id}")
    public UserDto updateUser(@RequestBody UserDto updateUserDto, @PathVariable Long id){
        try {
            User exist = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
            exist.setUsername(updateUserDto.getUsername());
            exist.setName(updateUserDto.getName());
            exist.setEmail(updateUserDto.getEmail());
            User updated = userRepo.save(exist);
            return UserMapper.toDto(updated);
        } catch (UserNotFoundException err){
            throw err;
        }
        catch (Exception err){
            throw new RuntimeException("errpr occured", err);
        }
    }
    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable Long id) {
        try {
            if (!userRepo.existsById(id)) {
                throw new UserNotFoundException(id);
            }
            userRepo.deleteById(id);
            return "User with id " + id + " has been deleted successfully.";
        } catch (UserNotFoundException err) {
            throw err;
        } catch (Exception err) {
            throw new RuntimeException("error occcured", err);
        }

    }

}
