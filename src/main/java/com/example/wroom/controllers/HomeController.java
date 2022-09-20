package com.example.wroom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to handle home page related requests
 *
 * @author Kristiina Lindre
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String showHomePage(@ModelAttribute("message") String message,
                               @ModelAttribute("messageType") String messageType) {
        return "home";
    }
}
