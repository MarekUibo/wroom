package com.example.wroom.services;

import com.example.wroom.exceptions.AuthorityNotFoundException;
import com.example.wroom.exceptions.BranchNotFoundException;
import com.example.wroom.exceptions.UserNotFoundException;
import com.example.wroom.models.Branch;
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
    void createUser(User user) throws AuthorityNotFoundException;

    /**
     * To create a new customer
     *
     * @param user User
     */
    //void createCustomer(User user);

    /**
     * To find user by username
     *
     * @param userName username
     * @return User
     */
    User findUserByUserName(String userName) throws UserNotFoundException;

    /**
     * To find user by username and password
     *
     * @param userName userName
     * @param password password
     * @return Optional of User
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
     *
     * @param id id of the user
     * @return User
     */
    User findUserById(UUID id) throws UserNotFoundException;


    /**
     * To find all users
     *
     */

    void updateUser(User employee) throws UserNotFoundException;

    /**
     * To delete an user by its ID
     * @param id id of the user
     */
    void deleteUserById(UUID id) throws UserNotFoundException;

    /**
     * To restore an user by its ID
     *
     * @param id id
     */
    void restoreUserById(UUID id) throws UserNotFoundException;

}
