package com.example.wroom.controllers;

import com.example.wroom.exceptions.AuthorityNotFoundException;
import com.example.wroom.exceptions.CarNotFoundException;
import com.example.wroom.exceptions.UserNotFoundException;
import com.example.wroom.models.User;
import com.example.wroom.services.AuthorityService;
import com.example.wroom.services.BranchService;
import com.example.wroom.services.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public String showUserListPage(Model model, @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        model.addAttribute("users", userService.findAllUsers());
        return "user/list-users";
    }

    @GetMapping("/{id}")
    public String showUserViewPage(@PathVariable UUID id, Model model,
                                  RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("user", userService.findUserById(id));
            return "user/view-user";
        } catch (UserNotFoundException e) {
            return handleUserNotFoundExceptionByID(id, redirectAttributes);
        }
    }

    @GetMapping("/create")
    public String showCreateUserPage(@ModelAttribute("user") User user,
                                     @ModelAttribute("message") String message,
                                     @ModelAttribute("messageType") String messageType,
                                     Model model) {
        model.addAttribute("authorities", authorityService.findAllAuthorities());
        model.addAttribute("homeBranches", branchService.findAllBranches());
        return "user/create-user";
    }

    @PostMapping
    public String createUser(User user, RedirectAttributes redirectAttributes) throws AuthorityNotFoundException {
        try {
            User searchUser = userService.findUserByUserName(user.getUserName());
            redirectAttributes.addFlashAttribute("message",
                    String.format("User(id=%s) already exists!", searchUser.getUserName()));
            redirectAttributes.addFlashAttribute("messageType", "error");

            return "redirect:/user/create";
        } catch (UserNotFoundException e) {

            userService.createUser(user);
            redirectAttributes.addFlashAttribute("message",
                    String.format("User(id=%s) created successfully!", user.getUserName()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/user/create";
        }
    }
    @GetMapping("/update/{id}")
    public String showUpdateUserPage(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes,
                                     @RequestParam(value = "user", required = false) User user) {
        model.addAttribute("authorities", authorityService.findAllAuthorities());
        model.addAttribute("homeBranches", branchService.findAllBranches());
        if (user == null) {
            try {
                model.addAttribute("user", userService.findUserById(id));
                model.addAttribute("homeBranch", branchService.findAllBranches());
            } catch (UserNotFoundException e) {
                return handleUserNotFoundExceptionByID(id, redirectAttributes);
            }
        }

        return "user/update-user";
    }


    @PostMapping("/update")
    public String updateUser(User user, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUser(user);
            redirectAttributes.addFlashAttribute("message",
                    String.format("User(id=%s) updated successfully!", user.getUserName()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/user";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("User(id=%s) not found!", user.getUserName()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/user";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUserById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("User(id=%s) deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/user";
        } catch (UserNotFoundException e) {
            return handleUserNotFoundExceptionByID(id, redirectAttributes);
        }
    }

    @GetMapping("/restore/{id}")
    public String restoreUser(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            userService.restoreUserById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("User(id=%s) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/user";
        } catch (UserNotFoundException e) {
            return handleUserNotFoundExceptionByID(id, redirectAttributes);
        }
    }
    // PRIVATE METHODS //
    private String handleUserNotFoundExceptionByID(UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                String.format("User(id=%s) not found!", id));
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/user";
    }

}
