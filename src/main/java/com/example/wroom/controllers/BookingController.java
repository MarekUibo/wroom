package com.example.wroom.controllers;

import com.example.wroom.exceptions.BookingNotFoundException;
import com.example.wroom.exceptions.CarNotFoundException;
import com.example.wroom.models.Booking;
import com.example.wroom.models.Car;
import com.example.wroom.models.CarBodyType;
import com.example.wroom.models.CarStatus;
import com.example.wroom.services.BookingService;
import com.example.wroom.services.BranchService;
import com.example.wroom.services.CarService;
import com.example.wroom.services.UserService;
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

    @Autowired
    public UserService userService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private CarService carService;

    @GetMapping
    public String showBookingListPage(Model model, @ModelAttribute("message") String message,
                                      @ModelAttribute("messageType") String messageType) {
        model.addAttribute("bookings", bookingService.findAllBookings());
        return "booking/list-booking";
    }

    @GetMapping("/{id}")
    public String showBookingViewPage(@PathVariable UUID id, Model model,
                                      RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("booking", bookingService.findBookingById(id));
            return "booking/view-booking";
        } catch (BookingNotFoundException e) {
            return handleBookingNotFoundExceptionById(id, redirectAttributes);
        }
    }

    @GetMapping("/create")
    public String showCreateBookingPage(@ModelAttribute("booking") Booking booking,
                                        @ModelAttribute("message") String message,
                                        @ModelAttribute("messageType") String messageType,
                                        Model model) {
        model.addAttribute("carRegistrationNumber", carService.findAllCars());
        model.addAttribute("homeBranch", branchService.findAllBranches());

        return "booking/create-booking";
    }

    @PostMapping
    public String createBooking(Booking booking, RedirectAttributes redirectAttributes) {
        try {
            Booking searchBooking = bookingService.findBookingById(booking.getId());
            redirectAttributes.addFlashAttribute("message",
                    String.format("Booking(%s) already exists!", searchBooking.getId()));
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

    @GetMapping("/update/{id}")
    public String showUpdateBookingPage(@PathVariable UUID id, String registrationNumber , Model model, RedirectAttributes redirectAttributes,
                                        @RequestParam(value = "booking", required = false) Booking booking) throws BookingNotFoundException {
        if (booking == null) {
            try {
                model.addAttribute("booking", bookingService.findBookingById(id));
                model.addAttribute("carStatus", CarStatus.values());
                //model.addAttribute("registrationNumber", carService.findCarByRegistrationNumber(registrationNumber));
                //model.addAttribute("dateFrom", booking.getDateFrom());
                //model.addAttribute("dateTo", booking.getDateTo());
                //model.addAttribute("homeBranch", branchService.findAllBranches());
            } catch (BookingNotFoundException e) {
                throw new RuntimeException(e);
            }


        }
        return "booking/update-booking";
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
    @GetMapping("/delete/{id}")
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
    @GetMapping("/restore/{id}")
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
