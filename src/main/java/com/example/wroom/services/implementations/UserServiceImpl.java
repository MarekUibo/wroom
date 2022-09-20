package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.UserNotFoundException;
import com.example.wroom.models.User;
import com.example.wroom.repository.UserRepository;
import com.example.wroom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of Employee Service
 *
 * @author Kristiina Lindre & Marek Uibo
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(UUID id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return optionalUser.get();
    }

    @Override
    public User findUserByUserName(String userName) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserName(userName);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(userName);
        }
        return optionalUser.get();
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserNameAndPassword (userName, password);

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException(userName, password);
        }
        return optionalUser.get();
    }

    @Override
    public void updateUser(User user) throws UserNotFoundException {
        if (findUserById(user.getId()) != null) {
            userRepository.saveAndFlush(user);
        }
    }

    @Override
    public void deleteUserById(UUID id) throws UserNotFoundException {
        User user = findUserById(id);
        user.setActive(false);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void restoreUserById(UUID id) throws UserNotFoundException {
        User employee = findUserById(id);
        employee.setActive(true);
        userRepository.saveAndFlush(employee);
    }
}
