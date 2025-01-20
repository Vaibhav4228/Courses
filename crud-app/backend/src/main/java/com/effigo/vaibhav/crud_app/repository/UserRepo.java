package com.effigo.vaibhav.crud_app.repository;

import com.effigo.vaibhav.crud_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

}
