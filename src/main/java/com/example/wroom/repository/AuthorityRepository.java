package com.example.wroom.repository;

import com.example.wroom.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle Authority related queries
 *
 * @author Jonathan Rigottier
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
    Optional<Authority> findByName (String name);
}

