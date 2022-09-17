package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.RentalOfficeNotFoundException;
import com.example.wroom.exceptions.UserNotFoundException;
import com.example.wroom.models.User;
import com.example.wroom.repository.SiteUserRepository;
import com.example.wroom.services.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Customized implementation of UserDetailsService
 *
 * @author Kristiina Lindre
 */
@Service
@Transactional
public class SiteUserServiceImpl implements SiteUserService {

    @Autowired
    private SiteUserRepository siteUserRepository;

    @Override
    public void createUser(User user) {

    }

    @Override
    public User findUserByUserName(String userName) throws UsernameNotFoundException {
        Optional<User> optionalUser = siteUserRepository.findUserWithUserName(userName);

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(userName);
        }
        return optionalUser.get();

    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) throws UserNotFoundException {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }
}
