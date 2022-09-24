package com.example.wroom.repository;

import com.example.wroom.models.Booking;
import com.example.wroom.models.Car;
import com.example.wroom.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository to handle booking related DB operations
 *
 * @author Marek Uibo & Jonathan Rigottier
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Optional<Booking> findByUser(User user);

    List<Booking> findByUserAndCar(User user, Car car);
}








