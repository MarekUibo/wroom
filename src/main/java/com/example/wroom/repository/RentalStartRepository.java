package com.example.wroom.repository;

import com.example.wroom.models.Person;
import com.example.wroom.models.RentalStart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle rentalStart related DB operations
 *
 * @author: Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface RentalStartRepository extends JpaRepository<RentalStart, UUID> {

    Optional<RentalStart> findByCustomerEmail (Person email);
}

