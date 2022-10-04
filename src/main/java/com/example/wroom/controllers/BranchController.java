package com.example.wroom.controllers;

import com.example.wroom.exceptions.BranchNotFoundException;
import com.example.wroom.exceptions.CarNotFoundException;
import com.example.wroom.models.*;
import com.example.wroom.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

/**
 * @author Marek Uibo
 */
@Controller
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public String showBranchListPage(Model model, @ModelAttribute("message") String message,
                                     @ModelAttribute("messageType") String messageType) {
        model.addAttribute("branches", branchService.findAllBranches());
        return "branch/list-of-branches";
    }

    @GetMapping("/{id}")
    public String showBranchViewPage(@PathVariable UUID id, Model model,
                                     RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("branch", branchService.findBranchById(id));
            return "branch/view-branch";
        } catch (BranchNotFoundException e) {
            return handleBranchNotFoundExceptionById(id, redirectAttributes);
        }
    }

    @GetMapping("/create")
    public String showCreateBranchPage(@ModelAttribute("branch") Branch branch,
                                       @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType,
                                       Model model) {
        model.addAttribute("branchCityList", BranchCityList.values());
        return "branch/create-branch";
    }

    @PostMapping
    public String createBranch(Branch branch, RedirectAttributes redirectAttributes) {
        try {
            Branch searchBranch = branchService.findBranchByFullAddress(branch.getFullAddress());
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch with address (%s) already exists!", branch.getFullAddress()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/branch/create";
        } catch (BranchNotFoundException e) {
            branchService.createBranch(branch);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch with address (%s) created successfully!", branch.getFullAddress()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/branch";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateBranchPage(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes,
                                    @RequestParam(value = "branch", required = false) Branch branch) {
        if (branch == null) {
            try {
                model.addAttribute("branch", branchService.findBranchById(id));
                model.addAttribute("branchCityList", BranchCityList.values());
            } catch (BranchNotFoundException e) {
                return handleBranchNotFoundExceptionById(id, redirectAttributes);
            }
        }

        return "branch/update-branch";
    }

    @PostMapping("/update")
    public String updateBranch(Branch branch, RedirectAttributes redirectAttributes) {
        try {
            branchService.updateBranch(branch);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(id=%s) updated successfully!", branch.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/branch";
        } catch (BranchNotFoundException e) {
            return handleBranchNotFoundExceptionById(branch.getId(), redirectAttributes);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBranch(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
        try {
            branchService.deleteBranchById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Branch(id=%s) deleted successfully!", id));
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
                    String.format("Branch(id=%s) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/branch";
        } catch (BranchNotFoundException e) {
            return handleBranchNotFoundExceptionById(id, redirectAttributes);
        }
    }


    // PRIVATE METHODS //
    private String handleBranchNotFoundExceptionById(UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                String.format("Branch(id=%s) not found!", id));
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/branch";
    }
}