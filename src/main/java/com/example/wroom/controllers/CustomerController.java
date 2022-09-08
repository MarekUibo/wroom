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
            Customer searchCustomer = customerService.findCustomerById(customer.getId());
            redirectAttributes.addFlashAttribute("message",
                    String.format("Customer(id=%d) already exists!", searchCustomer.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/customer/create";
        } catch (CustomerNotFoundException e) {
            customerService.createCustomer(customer);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Customer(id=%d) created successfully!", customer.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/customer/create";
        }
    }
    @PostMapping("/update")
    public String updateCustomer(Customer customer, RedirectAttributes redirectAttributes) {
        try {
            customerService.updateCustomer(customer);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Customer(id=%d) updated successfully!", customer.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/customer";
        } catch (CustomerNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("Customer(id=%d) not found!", customer.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/customer";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            customerService.deleteCustomerById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Customer(id=%d) deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/customer";
        } catch (CustomerNotFoundException e) {
            return handleCustomerNotFoundExceptionByID(id, redirectAttributes);
        }
    }
    @GetMapping("/restore/{id}")
    public String restoreCustomer(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            customerService.restoreCustomerById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Customer(id=%d) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/customer";
        } catch (CustomerNotFoundException e) {
            return handleCustomerNotFoundExceptionByID(id, redirectAttributes);
        }
    }
    // PRIVATE METHODS //
    private String handleCustomerNotFoundExceptionByID(UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                String.format("Customer(id=%s) not found!", id));
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/customer";
    }

}
