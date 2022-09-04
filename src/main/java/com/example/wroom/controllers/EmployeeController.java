package com.example.wroom.controllers;

import com.example.wroom.exceptions.EmployeeNotFoundException;
import com.example.wroom.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author:Marek Uibo
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeController employeeController;
    @GetMapping
    public String showEmployeeListPage(Model model, @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        model.addAttribute("message", employeeController.findAllEmployees());
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
            Employee searchEmployee = employeeServiceImpl.findEmployeeById(employee.getEmployeeId());
            redirectAttributes.addFlashAttribute("message",
                    String.format("Employee(%s) already exists!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/employee/create";
        } catch (EmployeeNotFoundException e) {
            employeeController.createEmployee(employee);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Employee(%s) created successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/employee/create";
        }
    }

}
