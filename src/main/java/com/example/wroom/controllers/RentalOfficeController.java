package com.example.wroom.controllers;

import com.example.wroom.exceptions.RentalOfficeNotFoundException;
import com.example.wroom.models.RentalOffice;
import com.example.wroom.services.RentalOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

/**
 * @author:Marek Uibo
 */
@Controller
@RequestMapping("/rentalOffice")
public class RentalOfficeController {

    @Autowired
    private RentalOfficeService rentalOfficeService;

    @GetMapping
    public String showRentalOfficeListPage(Model model, @ModelAttribute("message") String message,
                                           @ModelAttribute("messageType") String messageType) {
        model.addAttribute("rentalOffices", rentalOfficeService.findAllRentalOffices());
        return "rentalOffice/list-rentalOffice";
    }
    @GetMapping("/create")
    public String showCreateRentalOfficePage(@ModelAttribute("rentalOffice") RentalOffice rentalOffice,
                                             @ModelAttribute("message") String message,
                                             @ModelAttribute("messageType") String messageType) {
        return "rentalOffice/create-rentalOffice";
    }
    @PostMapping
    public String createRentalOffice(RentalOffice rentalOffice, RedirectAttributes redirectAttributes) {
        try {
            RentalOffice searchRentalOffice = rentalOfficeService.findRentalOfficeById(rentalOffice.getId());
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalOffice(id=%d) already exists!", rentalOffice.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/rentalOffice/create";
        } catch (RentalOfficeNotFoundException e) {
            rentalOfficeService.createRentalOffice(rentalOffice);
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalOffice(id=%d) created successfully!", rentalOffice.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/rentalOffice/create";
        }
    }
    @PostMapping("/update")
    public String updateRentalOffice(RentalOffice rentalOffice, RedirectAttributes redirectAttributes) {
        try {
            rentalOfficeService.updateRentalOffice(rentalOffice);
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalOffice(id=%d) updated successfully!", rentalOffice.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/rentalOffice";
        } catch (RentalOfficeNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalOffice(id=%d) not found!", rentalOffice.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/rentalOffice";
        }
    }
    @GetMapping("/delete")
    public String deleteRentalOffice(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            rentalOfficeService.deleteRentalOfficeById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalOffice(id=%d) deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/rentalOffice";
        } catch (RentalOfficeNotFoundException e) {
            return handleRentalOfficeNotFoundExceptionById(id, redirectAttributes);
        }
    }
    @GetMapping("/restore")
    public String restoreRentalOffice(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            rentalOfficeService.restoreRentalOfficeById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalOffice(id=%d) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/rentalOffice";
        } catch (RentalOfficeNotFoundException e) {
            return handleRentalOfficeNotFoundExceptionById(id, redirectAttributes);
        }
    }
    //Private methods//
    private String handleRentalOfficeNotFoundExceptionById(UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                String.format("RentalOffice(id=%d) not found!", id));
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/rentalOffice";
    }


}
