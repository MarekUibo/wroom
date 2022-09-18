package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.UserNotFoundException;
import com.example.wroom.models.User;
import com.example.wroom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Customized implementation of UserDetailsService
 *
 * @author Vinod John
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findUserByUserName(username);
            return new CustomUserDetails(user);
        } catch (UserNotFoundException userNotFoundException) {
            throw new UsernameNotFoundException(userNotFoundException.getLocalizedMessage());
        }
    }
}
