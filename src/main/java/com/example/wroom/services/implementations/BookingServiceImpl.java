package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.BookingNotFoundException;
import com.example.wroom.exceptions.CarNotFoundException;
import com.example.wroom.models.Booking;
import com.example.wroom.models.Car;
import com.example.wroom.models.User;
import com.example.wroom.repository.BookingRepository;
import com.example.wroom.services.BookingService;
import com.example.wroom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Implementation of Booking Service
 *
 * @author Jonathan Rigottier
 */
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Override
    public void createBooking(Booking booking)  {
        booking.setActive(true);
        bookingRepository.saveAndFlush(booking);
    }

    @Override
    public Booking findBookingById(UUID id) throws BookingNotFoundException {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isEmpty()) {
            throw new BookingNotFoundException(id);
        }

        return optionalBooking.get();
    }

    @Override
    public Booking findBookingByUser(User user) throws BookingNotFoundException {
        Optional<Booking> optionalBooking = bookingRepository.findByUser(user);

        if (optionalBooking.isEmpty()) {


            throw new BookingNotFoundException(user.getUserName());
        }

        return optionalBooking.get();
    }

    @Override
    public Booking findBookingByReferenceNumber(String bookingReferenceNumber) throws BookingNotFoundException {
        Optional<Booking> optionalBooking = bookingRepository.findByBookingReferenceNumber(bookingReferenceNumber);

        if(optionalBooking.isEmpty()) {
        throw new BookingNotFoundException(bookingReferenceNumber);
    }
        return optionalBooking.get();
    }

    @Override
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void updateBooking(Booking booking) throws BookingNotFoundException {
        if (findBookingById(booking.getId()) != null) {
            bookingRepository.saveAndFlush(booking);
        }
    }

    @Override
    public void deleteBookingById(UUID id) throws BookingNotFoundException {
        Booking booking = findBookingById(id);
        booking.setActive(false);
        bookingRepository.saveAndFlush(booking);
    }

    @Override
    public void restoreBookingById(UUID id) throws BookingNotFoundException {
        Booking booking = findBookingById(id);
        booking.setActive(true);
        bookingRepository.saveAndFlush(booking);
    }

    // PRIVATE METHODS
    private boolean isBookingValid(Booking booking) {
        List<Booking> bookings = bookingRepository.findByUserAndCar(booking.getUser(), booking.getCar());

        long bookingCount = bookings.stream()
                .filter(booking1 -> booking1.isActive() && isValidBookingDate(booking.getDateFrom(), booking1.getDateFrom(), true)
                        && isValidBookingDate(booking.getDateTo(), booking1.getDateTo(), false))
                .count();

        return bookingCount == 0;
    }

    private boolean isValidBookingDate(LocalDate bookingDate, LocalDate existingBookingDate, boolean isFromDate) {
        if (isFromDate) {
            return existingBookingDate.isAfter(bookingDate) || existingBookingDate.isEqual(bookingDate);
        } else {
            return existingBookingDate.isBefore(bookingDate) || existingBookingDate.isEqual(bookingDate);
        }
    }
}
