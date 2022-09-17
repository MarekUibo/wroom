package com.example.wroom.services;

import com.example.wroom.exceptions.UserNotFoundException;
import com.example.wroom.models.User;
import java.util.List;
import java.util.UUID;

/**
 * Service to handle user related operations
 *
 * @author Kristiina Lindre & Marek Uibo
 */
public interface UserService {
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

    /**
     * To find an user by its ID
     * @param id id of the user
     * @return User
     */
    User findUserById(UUID id) throws UserNotFoundException;

    /**
     * To find an user by its email
     * @param email Email of the user
     * @return User
     */
    User findUserByEmail(String email) throws UserNotFoundException;

    /**
     * To find all users
     * @return List of users
     */

    void updateUser(User employee) throws UserNotFoundException;

    /**
     * To delete an user by its ID
     * @param id id of the user
     */
    void deleteUserById(UUID id) throws UserNotFoundException;

    /**
     * To restore an user by its ID
     * @param id
     */
    void restoreUserById(UUID id) throws UserNotFoundException;

}
