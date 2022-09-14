package com.example.wroom.repository;

import com.example.wroom.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle siteUser related DB operations
 *
 * @author Marek Uibo & Jonathan Rigottier
 */
public interface SiteUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserWithUserName(String userName);
}
