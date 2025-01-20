package com.jobApp.JobSearchApp.service;

import com.jobApp.JobSearchApp.entity.User;
import com.jobApp.JobSearchApp.entity.UserPrincipal;
import com.jobApp.JobSearchApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService {
    @Autowired
    private UserRepo repo;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= repo.findByUsername(username);

        if (user==null) {
            // System.out.println("User 404");
            throw new UsernameNotFoundException("User 404");
        }

        return new UserPrincipal(user);
    }
}
