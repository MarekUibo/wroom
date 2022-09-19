package com.example.wroom.controllers;


import com.example.wroom.models.Login;
import com.example.wroom.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller to handle login related requests
 *
 * @author Jonathan Rigottier
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    public String showLoginPage(@ModelAttribute("Login") Login login,
                                @ModelAttribute("message") String message,
                                @ModelAttribute("messageType") String messageType) {
        return "auth/login";
    }

    @PostMapping
    public String postLogin(Login login, RedirectAttributes redirectAttributes) {
        if(loginService.isLoginValid(login)) {
            redirectAttributes.addFlashAttribute("message", "Login successful!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("message", "Invalid username or password!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/login";
        }
    }
}
