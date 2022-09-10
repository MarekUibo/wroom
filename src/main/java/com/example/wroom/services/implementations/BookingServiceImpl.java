package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.BookingNotFoundException;
import com.example.wroom.exceptions.CustomerNotFoundException;
import com.example.wroom.models.Booking;
import com.example.wroom.models.Customer;
import com.example.wroom.repository.BookingRepository;
import com.example.wroom.services.BookingService;
import com.example.wroom.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private CustomerService customerService;

    @Override
    public void createBooking(Booking booking) {
        booking.setActive(true);
        bookingRepository.save(booking);
    }

    @Override
    public Booking findBookingById(UUID id) throws BookingNotFoundException {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if(optionalBooking.isEmpty()) {
            throw new BookingNotFoundException(id);
        }

        return optionalBooking.get();
    }

    @Override
    public Booking findBookingByCustomerEmail(String email) throws BookingNotFoundException, CustomerNotFoundException {
        Customer customer = customerService.findCustomerByEmail(email);

        Optional<Booking> optionalBooking = bookingRepository.findByCustomer(customer);

        if(optionalBooking.isEmpty()) {
            throw new BookingNotFoundException(customer);
        }

        return optionalBooking.get();
    }

    @Override
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void updateBooking(Booking booking) throws BookingNotFoundException {
        if(findBookingById(booking.getId()) !=null) {
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
}
