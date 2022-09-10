package com.example.wroom.controllers;

import com.example.wroom.exceptions.BookingNotFoundException;
import com.example.wroom.models.Booking;
import com.example.wroom.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * @author:Marek Uibo
 */
@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    public BookingService bookingService;

    @GetMapping
    public String showBookingListPage(Model model, @ModelAttribute("message") String message,
                                      @ModelAttribute("messageType") String messageType) {
        model.addAttribute("bookings", bookingService.findAllBookings());
        return "booking/list-booking";
    }
    @GetMapping ("/create")
    public String showCreateBookingPage(@ModelAttribute("booking")Booking booking,
                                        @ModelAttribute("message") String message,
                                        @ModelAttribute("messageType") String messageType){
        return "booking/create-booking";
    }
    @PostMapping
    public String createBooking(Booking booking, RedirectAttributes redirectAttributes) {
        try {
            Booking searchBooking = bookingService.findBookingById(booking.getId());
            redirectAttributes.addFlashAttribute("message",
                    String.format("Booking(%s) already exists!", booking.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/booking/create";
        } catch (BookingNotFoundException e) {
            bookingService.createBooking(booking);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Booking(%s) created successfully!", booking.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/booking";
        }
    }

    @GetMapping("/update")
    public String updateBooking(Booking booking, RedirectAttributes redirectAttributes) {
            try {
               bookingService.updateBooking(booking);
               redirectAttributes.addFlashAttribute("message",
                       String.format("Booking(%s) updated successfully!", booking.getId()));
            } catch (BookingNotFoundException e) {
                redirectAttributes.addFlashAttribute("message", e.getMessage());
                redirectAttributes.addFlashAttribute("messageType", "error");
                return "redirect:/booking";
    }
        return null;
    }
    @GetMapping("/delete")
    public String deleteBooking(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            bookingService.deleteBookingById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Booking(%s) deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/booking";
        } catch (BookingNotFoundException e) {
           return handleBookingNotFoundExceptionById(id, redirectAttributes);
        }
    }
    @GetMapping("/restore")
    public String restoreBooking(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            bookingService.restoreBookingById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Booking(%s) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/booking";
        } catch (BookingNotFoundException e) {
            return handleBookingNotFoundExceptionById(id, redirectAttributes);
        }
    }
    //Private methods//
    private String handleBookingNotFoundExceptionById(UUID id, RedirectAttributes redirectAttributes) {
       redirectAttributes.addFlashAttribute("message",
               String.format("Booking(%s) not found!", id));
       redirectAttributes.addFlashAttribute("messageType", "error");
       return "redirect:/booking";
    }


}
