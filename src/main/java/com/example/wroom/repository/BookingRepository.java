package com.example.wroom.repository;

import com.example.wroom.models.Booking;
import com.example.wroom.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle booking related DB operations
 *
 * @author Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Optional<Booking> findByCustomer(Customer customer);
}








