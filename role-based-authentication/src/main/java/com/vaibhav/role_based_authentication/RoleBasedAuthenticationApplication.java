package com.vaibhav.role_based_authentication;

import com.vaibhav.role_based_authentication.entity.Role;
import com.vaibhav.role_based_authentication.entity.User;
import com.vaibhav.role_based_authentication.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RoleBasedAuthenticationApplication implements CommandLineRunner {
	@Autowired
	private UserRepo userRepo;


	public static void main(String[] args) {
		SpringApplication.run(RoleBasedAuthenticationApplication.class, args);
	}

	public void run(String... args){
		User adminAccount = userRepo.findByRole(Role.ADMIN);
		if(null == adminAccount){
			User user = new User();

			user.setEmail("admin@gmail.com");
			user.setFirstName("admin");
			user.setLastName("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepo.save(user);
		}
	}

}
