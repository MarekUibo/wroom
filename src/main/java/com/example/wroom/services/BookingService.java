package com.example.wroom.services;

import com.example.wroom.exceptions.BookingNotFoundException;

import com.example.wroom.exceptions.UserNotFoundException;

import com.example.wroom.models.Booking;
import com.example.wroom.models.User;

import java.util.List;
import java.util.UUID;

/**
 * To handle booking related operations
 *
 * @author Rigottier Jonathan
 */
public interface BookingService {
    /**
     * To create a new booking
     * @param booking Booking
     */
    void createBooking(Booking booking);

    /**
     * To find a booking by its ID
     * @param id id of the booking
     * @return Booking
     */
    Booking findBookingById(UUID id) throws BookingNotFoundException;

    /**
     * To find a booking by its user email
     * @param userName userName the user's booking
     * @return Booking
     */
    Booking findBookingByUserName(User userName) throws BookingNotFoundException, UserNotFoundException;

    /**
     * To find all bookings
     * @return List of bookings
     */
    List<Booking> findAllBookings();

    /**
     * To update an existing booking
     * @param booking Booking
     */
    void updateBooking(Booking booking) throws BookingNotFoundException;

    /**
     * To delete a booking by its ID
     * @param id id of the booking
     */
    void deleteBookingById(UUID id) throws BookingNotFoundException;

    /**
     * To restore a course by its ID
     * @param id
     */
    void restoreBookingById(UUID id) throws BookingNotFoundException;
}