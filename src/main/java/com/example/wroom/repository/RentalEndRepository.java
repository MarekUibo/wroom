package com.example.wroom.repository;

import com.example.wroom.models.Person;
import com.example.wroom.models.RentalEnd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle rentalEnd related DB operations
 *
 * @author: Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface RentalEndRepository extends JpaRepository<RentalEnd, UUID> {

    Optional<RentalEnd> findByCustomerEmail(Person email);
}

