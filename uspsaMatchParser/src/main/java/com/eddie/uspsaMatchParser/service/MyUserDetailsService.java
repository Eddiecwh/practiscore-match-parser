package com.eddie.uspsaMatchParser.service;

import com.eddie.uspsaMatchParser.models.User;
import com.eddie.uspsaMatchParser.models.UserPrincipal;
import com.eddie.uspsaMatchParser.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            System.out.println("The username: " + username + " was not found");
            throw new UsernameNotFoundException("The username: " + username + " was not found");
        }

        return new UserPrincipal(user);
    }
}
