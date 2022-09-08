package com.example.wroom.controllers;

import com.example.wroom.exceptions.BranchNotFoundException;
import com.example.wroom.models.Branch;
import com.example.wroom.services.BranchService;
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
    private BranchService branchService;

    @GetMapping
    public String showBranchListPage(Model model, @ModelAttribute("message") String message,
                                     @ModelAttribute("messageType") String messageType) {
        model.addAttribute("message", branchService.findAllBranches());
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
            Branch searchBranch = branchService.findBranchById(branch.getId());
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(id=%d) already exists!", branch.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/branch/create";
        } catch (BranchNotFoundException e) {
            branchService.createBranch(branch);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(id=%d) created successfully!", branch.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/branch/create";
        }
    }
    @GetMapping("/update")
    public String updateBranch(Branch branch, RedirectAttributes redirectAttributes) {
        try {
            branchService.updateBranch(branch);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(id=%d) updated successfully!", branch.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/branch";
        } catch (BranchNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(id=%d) not found!", branch.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/branch";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBranch(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
        try {
            branchService.deleteBranchById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(id=%d) deleted successfully!"));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/branch";
        } catch (BranchNotFoundException e) {

            return handleBranchNotFoundExceptionById(id, redirectAttributes);
        }
    }

    @GetMapping("/restore/{id}")
    public String restoreBranch(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            branchService.restoreBranchById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(id=%d) restored successfully!"));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/branch";
        } catch (BranchNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(%s) not found!"));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/branch";
        }
    }


    // PRIVATE METHODS //
    private String handleBranchNotFoundExceptionById(UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                String.format("Branch(id=%d) not found!", id));
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/branch";
    }
}