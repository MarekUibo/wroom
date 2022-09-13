package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.UserNotFoundException;
import com.example.wroom.models.User;
import com.example.wroom.services.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Customized implementation of UserDetailsService
 *
 * @author Kristiina Lindre
 */
@Service
public class SiteUserDetailsService implements SiteUserDetailsService {
    @Autowired
    private SiteUserService siteUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = siteUserService.findUserByUserName(username);
            return new UserDetails(user);
        } catch (UserNotFoundException userNotFoundException) {
            throw new UsernameNotFoundException(userNotFoundException.getLocalizedMessage());
        }
    }
}
