package com.example.wroom.controllers;

import com.example.wroom.exceptions.RentalEndNotFoundException;
import com.example.wroom.models.RentalEnd;
import com.example.wroom.services.RentalEndService;
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
@RequestMapping("/returnEnd")
public class RentalEndController {

    @Autowired
    private RentalEndService rentalEndService;

    @GetMapping
    public String showRentalEndListPage(Model model, @ModelAttribute("message") String message,
                                        @ModelAttribute("messageType") String messageType) {
        model.addAttribute("rentalEnds", rentalEndService.findAllRentalEnds());
        return "rentalEnd/list-rentalEnd";
    }
    @GetMapping("/create")
    public String showCreateRentalEndPage(@ModelAttribute("rentalEnd") RentalEnd rentalEnd,
                                          @ModelAttribute("message") String message,
                                          @ModelAttribute("messageType") String messageType) {
        return "rentalEnd/create-rentalEnd";
    }
    @PostMapping
    public String createRentalEnd(RentalEnd rentalEnd, RedirectAttributes redirectAttributes) {
        try {
            RentalEnd searchRentalEnd = rentalEndService.findRentalEndById(rentalEnd.getId());
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalEnd(id=%d) already exists!", searchRentalEnd.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/rentalEnd/create";
        } catch (RentalEndNotFoundException e) {
            rentalEndService.createRentalEnd(rentalEnd);
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalEnd(id=%d) created successfully!", rentalEnd.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/rentalEnd/create";
        }
    }
    @PostMapping("/update")
    public String updateRentalEnd(RentalEnd rentalEnd, RedirectAttributes redirectAttributes) {
        try {
            rentalEndService.updateRentalEnd(rentalEnd);
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalEnd(id=%d) updated successfully!", rentalEnd.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/rentalEnd/update";
        } catch (RentalEndNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalEnd(id=%d) not found!", rentalEnd.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/rentalEnd/update";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteRentalEnd(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            rentalEndService.deleteRentalEndById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalEnd(id=%d) deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/rentalEnd";
        } catch (RentalEndNotFoundException e) {
            return handleRentalEndNotFoundExceptionById(id, redirectAttributes);
        }
    }
    @GetMapping("/restore/{id}")
    public String restoreRentalEnd(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            rentalEndService.restoreRentalEndById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("RentalEnd(id=%d) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/rentalEnd";
        } catch (RentalEndNotFoundException e) {
            return handleRentalEndNotFoundExceptionById(id, redirectAttributes);
        }
    }
    //Private methods//
    private String handleRentalEndNotFoundExceptionById(UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                String.format("RentalEnd(id=%d) not found!", id));
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/rentalEnd";
    }

}
