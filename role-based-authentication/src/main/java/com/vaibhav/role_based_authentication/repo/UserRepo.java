package com.vaibhav.role_based_authentication.repo;

import com.vaibhav.role_based_authentication.entity.Role;
import com.vaibhav.role_based_authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);

    User findByRole(Role role);


}
