package com.example.wroom.controllers;

import com.example.wroom.exceptions.UserNotFoundException;
import com.example.wroom.models.User;
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

    @GetMapping
    public String showUserListPage(Model model, @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        model.addAttribute("users", userService.findAllUsers());
        return "user/list-users";
    }

    @GetMapping("/create")

    public String showCreateUserPage(@ModelAttribute("user") User user,
                                         @ModelAttribute("message") String message,
                                         @ModelAttribute("messageType") String messageType) {
        return "user/create-user";
    }
    @PostMapping
    public String createUser(User user, RedirectAttributes redirectAttributes) {
        try {
            User searchUser = userService.findUserById(user.getId());
            redirectAttributes.addFlashAttribute("message",
                    String.format("User(id=%d) already exists!", searchUser.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");

            return "redirect:/user/create";
        } catch (UserNotFoundException e) {

            userService.createUser(user);
            redirectAttributes.addFlashAttribute("message",
                    String.format("User(id=%d) created successfully!", user.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/user/create";
        }
    }
    @PostMapping("/update")
    public String updateUser(User user, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUser(user);
            redirectAttributes.addFlashAttribute("message",
                    String.format("User(id=%d) updated successfully!", user.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/user";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("User(id=%d) not found!", user.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/user";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUserById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("User(id=%d) deleted successfully!", id));
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
                    String.format("User(id=%d) restored successfully!", id));
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
