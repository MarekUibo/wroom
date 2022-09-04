package com.example.wroom.controllers;

import com.example.wroom.exceptions.BranchNotFoundException;
import com.example.wroom.models.Branch;
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
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchController branchController;

    @GetMapping
    public String showBranchListPage(Model model, @ModelAttribute("message") String message,
                                     @ModelAttribute("messageType") String messageType) {
        model.addAttribute("message", branchController.findAllBranches());
        return "branch/list-branch";
    }

    @GetMapping("/create")
    public String showCreateBranchPage(@ModelAttribute("branch") Branch branch,
                                       @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        return "branch/create-branch";
    }

    @PostMapping
    public String createBranch(Branch branch, RedirectAttributes redirectAttributes) {
        try {
            Branch searchBranch = branchController.findBranchById(UUID id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(%s) already exists!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/branch/create";
        } catch (BranchNotFoundException e) {
            branchController.createBranch(branch);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(%s) created successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/branch/create";
        }
    }
    public String updateBranch(Branch branch, RedirectAttributes redirectAttributes) {
        try {
            branchController.updateBranch(branch);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(%s) updated successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/branch";
        } catch (BranchNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(%s) not found!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/branch";
        }
    }
    @GetMapping ("/delete")
    public String deleteBranch(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
        try {
            branchController.deleteBranch(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(%s) deleted successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/branch";
        } catch (BranchNotFoundException e) {

            return BranchNotFoundException(UUID id,redirectAttributes);
        }
    }

    public String restoreBranch(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
        try {
            branchController.restoreBranch(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(%s) restored successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/branch";
        } catch (BranchNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(%s) not found!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/branch";
        }
    }

}
