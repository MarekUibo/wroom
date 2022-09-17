package com.example.wroom.repository;

import com.example.wroom.models.Branch;
import com.example.wroom.models.RentalOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle rentalOffice related DB operations
 *
 * @author Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface RentalOfficeRepository extends JpaRepository<RentalOffice, UUID> {

    Optional<RentalOffice> findByName(String name);

}

