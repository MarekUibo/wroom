package com.example.wroom.controllers;

import com.example.wroom.exceptions.CustomerNotFoundException;
import com.example.wroom.models.Customer;
import com.example.wroom.services.CustomerService;
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

    @GetMapping("/create")
    public String showCreateCustomerPage(@ModelAttribute("customer") Customer customer,
                                         @ModelAttribute("message") String message,
                                         @ModelAttribute("messageType") String messageType) {
        return "customer/create-customer";
    }
    @PostMapping
    public String createCustomer(Customer customer, RedirectAttributes redirectAttributes) {
        try {
            Customer searchCustomer = customerService.findCustomerById(UUID id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Customer(%s) already exists!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/customer/create";
        } catch (CustomerNotFoundException e) {
            customerService.createCustomer(customer);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Customer(%s) created successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/customer/create";
        }
    }

    public String updateCustomer(Customer customer, RedirectAttributes redirectAttributes) {
        try {
            customerService.updateCustomer(customer);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Customer(%s) updated successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/customer";
        } catch (CustomerNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("Customer(%s) not found!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/customer";
        }
    }

    @GetMapping("/delete")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            customerService.deleteCustomerById(UUID id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Customer(%s) deleted successfully!", UUID id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/customer";
        } catch (CustomerNotFoundException e) {
            return new CustomerNotFoundException(UUID id, redirectAttributes);
        }
    }
    @GetMapping("/restore")
    public String restoreCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            customerService.restoreCustomerById(UUID id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Customer(%s) restored successfully!", UUID id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/customer";
        } catch (CustomerNotFoundException e) {
            return new CustomerNotFoundException(UUID id, redirectAttributes);
        }
    }


}
