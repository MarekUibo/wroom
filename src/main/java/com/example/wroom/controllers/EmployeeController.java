package com.example.wroom.controllers;

import com.example.wroom.exceptions.EmployeeNotFoundException;
import com.example.wroom.models.Employee;
import com.example.wroom.models.Person;
import com.example.wroom.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

/**
 * @author: Marek Uibo
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String showEmployeeListPage(Model model, @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        model.addAttribute("message", employeeService.findAllEmployees());
        return "employee/list-employee";
    }
    @GetMapping("/create")
    public String showCreateEmployeePage(@ModelAttribute("employee") Employee employee,
                                         @ModelAttribute("message") String message,
                                         @ModelAttribute("messageType") String messageType) {
        return "employee/create-employee";
    }
    @PostMapping
    public String createEmployee(Employee employee, RedirectAttributes redirectAttributes) {
        try {
            Employee searchEmployee = employeeService.findEmployeeById(employee.getId());
            redirectAttributes.addFlashAttribute("message",
                    String.format("Employee(id=%d) already exists!", searchEmployee.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/employee/create";
        } catch (EmployeeNotFoundException e) {
            employeeService.createEmployee(employee);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Employee(id=%d) created successfully!", employee.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/employee/create";
        }
    }
    @GetMapping("/update")
    public String updateEmployee(Employee employee, RedirectAttributes redirectAttributes) {
        try {
            employeeService.updateEmployee(employee);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Employee(id=%d) updated successfully!", employee.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/employee";
        } catch (EmployeeNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("Employee(id=%d) not found!", employee.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/employee";
        }
    }
    @GetMapping("/delete")
    public String deleteEmployee(@PathVariable UUID employeeId, RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteEmployeeById(employeeId);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Employee(id=%d) deleted successfully!", employeeId));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/employee";
        } catch (EmployeeNotFoundException e) {

            return handleEmployeeNotFoundExceptionById(employeeId, redirectAttributes);

            }

    }
    @GetMapping("/restore/{id}")
    public String restoreEmployee(@PathVariable UUID employeeId, RedirectAttributes redirectAttributes) {
        try {
            employeeService.restoreEmployeeById(employeeId);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Employee(id=%d) restored successfully!", employeeId));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/employee";
        } catch (EmployeeNotFoundException e) {
            return handleEmployeeNotFoundExceptionById(employeeId, redirectAttributes);
        }
    }
    //Private methods//
    private String handleEmployeeNotFoundExceptionById(UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                String.format("Employee(id=%d) not found!", id));
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/employee";
    }

}
