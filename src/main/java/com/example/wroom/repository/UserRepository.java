package com.example.wroom.repository;

import com.example.wroom.models.Branch;
import com.example.wroom.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle siteUser related DB operations
 *
 * @author Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByUserNameAndPassword(String userName, String password);

    Optional<User> findById(UUID id);

}
