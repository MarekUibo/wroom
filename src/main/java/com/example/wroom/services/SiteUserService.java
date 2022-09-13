package com.example.wroom.services;

import com.example.wroom.exceptions.UserNotFoundException;
import com.example.wroom.models.User;
import java.util.List;

/**
 * Service to handle user related operations
 *
 * @author Kristiina Lindre
 */
public interface SiteUserService {
    /**
     * To create a new user
     *
     * @param user User
     */
    void createUser(User user);

    /**
     * To find user by userName
     *
     * @param userName userName
     * @return User
     */
    User findUserByUserName(String userName) throws UserNotFoundException;


    /**
     * To find user by username and password
     *
     * @param userName userName
     * @param password password
     * @return User
     */
   User findUserByUserNameAndPassword(String userName, String password) throws UserNotFoundException;

    /**
     * To fina all users
     *
     * @return list of users
     */
    List<User> findAllUsers();
}
