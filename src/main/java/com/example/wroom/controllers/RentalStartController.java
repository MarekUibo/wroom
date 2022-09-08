package com.example.wroom.controllers;

import com.example.wroom.exceptions.RentalStartNotFoundException;
import com.example.wroom.models.RentalStart;
import com.example.wroom.services.RentalStartService;
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
@RequestMapping("/rentalStart")
public class RentalStartController {

    @Autowired
    private RentalStartService rentalStartService;

    @GetMapping
    public String showRentalStartListPage(Model model, @ModelAttribute("message") String message,
                                            @ModelAttribute("messageType") String messageType) {
        model.addAttribute("rentalStarts", rentalStartService.findAllRentalStarts());
        return "rentalStart/list-rentalStart";
    }
    @GetMapping("/create")
    public String showCreateRentalStartPage(@ModelAttribute("rentalStart") RentalStart rentalStart,
                                            @ModelAttribute("message") String message,
                                            @ModelAttribute("messageType") String messageType) {
        return "rentalStart/create-rentalStart";
    }
    @PostMapping
    public String createRentalStart(RentalStart rentalStart, RedirectAttributes redirectAttributes) {
        try {
            RentalStart searchRentalStart = rentalStartService.findRentalStartById(rentalStart.getId());
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalStart(id=%d) already exists!", rentalStart.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/rentalStart/create";
        } catch (RentalStartNotFoundException e) {
            rentalStartService.createRentalStart(rentalStart);
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalStart(id=%d) created successfully!", rentalStart.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/rentalStart/create";
        }
    }

    public String updateRentalStart(RentalStart rentalStart, RedirectAttributes redirectAttributes) {
        try {
            rentalStartService.updateRentalStart(rentalStart);
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalStart(id=%d) updated successfully!", rentalStart.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/rentalStart/update";
        } catch (RentalStartNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalStart(id=%d) not found!", rentalStart.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/rentalStart/update";
        }
    }
    @GetMapping("/delete")
    public String deleteRentalStart(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            rentalStartService.deleteRentalStartById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalStart(id=%d) deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/rentalStart";
        } catch (RentalStartNotFoundException e) {
            return handleRentalStartNotFoundExceptionById(id, redirectAttributes);
        }
    }
    @GetMapping("/restore")
    public String restoreRentalStart(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            rentalStartService.restoreRentalStartById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalStart(id=%d) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/rentalStart";
        } catch (RentalStartNotFoundException e) {
            return handleRentalStartNotFoundExceptionById(id, redirectAttributes);
        }
    }

    //Private methods//
    private String handleRentalStartNotFoundExceptionById(UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                String.format("RentalStart(id=%d) not found!", id));
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/rentalStart";
    }



}

