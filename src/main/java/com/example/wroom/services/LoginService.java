package com.example.wroom.services;


import com.example.wroom.models.Login;

/**
 * Service to handle login related operations
 *
 * @author Jonathan Rigottier
 */
public interface LoginService {
    /**
     * To check whether the login is valid or not
     *
     * @param login Login
     * @return true or false
     */
    boolean isLoginValid(Login login);
}
