package com.example.wroom.repository;
/**
 * @author:Marek Uibo
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.wroom.models.Booking;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {


    Optional<Booking> findBookingByName(String name);
}
//comments:








