package com.example.wroom.repository;

import com.example.wroom.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle car related DB operations
 *
 * @author  Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {

    Optional<Car> findByRegistrationNumber(String registrationNumber);
}

