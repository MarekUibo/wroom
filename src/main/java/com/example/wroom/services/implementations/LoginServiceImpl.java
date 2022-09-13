package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.UserNotFoundException;
import com.example.wroom.models.Login;
import com.example.wroom.services.LoginService;
import com.example.wroom.services.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of LoginService
 *
 * @author Kristiina Lindre
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private SiteUserService customUserService;

    @Override
    public boolean isLoginValid(Login login) {
        try {
            customUserService.findUserByUserNameAndPassword(login.getUserName(), login.getPassword());
            return true;
        } catch (UserNotFoundException userNotFoundException) {
            return false;
        }
    }
}
