package com.example.wroom.repository;

import com.example.wroom.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.wroom.models.Booking;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle booking related DB operations
 *
 * @author: Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {



    Optional<Booking> findBookingByName(String name);

    Optional<Booking> findByCustomerEmail(Person email);

}








