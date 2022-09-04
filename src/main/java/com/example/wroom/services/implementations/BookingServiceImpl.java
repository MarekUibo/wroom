package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.BookingNotFoundException;
import com.example.wroom.models.Booking;
import com.example.wroom.models.Customer;
import com.example.wroom.services.BookingService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of Booking Service
 *
 * @author Jonathan Rigottier
 */
@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Override
    public void createBooking(Booking booking) {

    }

    @Override
    public Booking findBookingById(UUID id) throws BookingNotFoundException {
        return null;
    }

    @Override
    public Booking findBookingByCustomerEmail(Customer email) throws BookingNotFoundException {
        return null;
    }

    @Override
    public List<Booking> findAllBookings() {
        return null;
    }

    @Override
    public void updateBooking(Booking booking) throws BookingNotFoundException {

    }

    @Override
    public void deleteBookingById(UUID id) throws BookingNotFoundException {

    }

    @Override
    public void restoreBookingById(UUID id) throws BookingNotFoundException {

    }
}
