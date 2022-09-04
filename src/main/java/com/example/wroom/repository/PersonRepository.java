package com.example.wroom.repository;

import com.example.wroom.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle person related DB operations
 *
 * @author: Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> findByEmail(String email);
}

