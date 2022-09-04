package com.example.wroom.controllers;

import com.example.wroom.models.Customer;
import com.example.wroom.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:Marek Uibo
 */
@Controller
@RequestMapping("/costumer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String showCustomerListPage(Model model, @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        model.addAttribute("customers", customerService.findAllCustomers());
        return "customer/list-customer";
    }

    public String showCreateCustomerPage(@ModelAttribute("customer") Customer customer,
                                         @ModelAttribute("message") String message,
                                         @ModelAttribute("messageType") String messageType) {
        return "customer/create-customer";
    }
}
