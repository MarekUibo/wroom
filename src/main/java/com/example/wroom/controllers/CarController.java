package com.example.wroom.controllers;

import com.example.wroom.exceptions.CarNotFoundException;
import com.example.wroom.models.Car;
import com.example.wroom.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author:Marek Uibo
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public String showCarListPage(Model model, @ModelAttribute("message") String message,
                                  @ModelAttribute("messageType") String messageType) {
        model.addAttribute("cars", carService.findAllCars());
        return "car/list-car";
    }
    @GetMapping ("/create")
    public String showCreateCarPage(@ModelAttribute("car") Car car,
                                    @ModelAttribute("message") String message,
                                    @ModelAttribute("messageType") String messageType) {
        return "car/create-car";
    }
    @PostMapping
    public String createCar(Car car, RedirectAttributes redirectAttributes) {
        try {
            Car searchSchool = carService.findCarByName(car.getName());
            redirectAttributes.addFlashAttribute("message",
                    String.format("Car(%s) already exists!", searchSchool.getName()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/car/create";
        } catch (CarNotFoundException e) {
            carService.createCar(car);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Car(%s) created successfully!", car.getName()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/car/create";
        }
    }
    @PostMapping("/update")
    public String updateCar(Car car, RedirectAttributes redirectAttributes) {
            try {
                model.addAttribute("car", carService.findCarById(id));
            } catch (CarNotFoundException e) {
                redirectAttributes.addFlashAttribute("message", e.getMessage());
                redirectAttributes.addFlashAttribute("messageType", "error");
                return "redirect:/car";
            }
        }
    }

    @GetMapping("/delete")
    public String deleteCar(Long id, RedirectAttributes redirectAttributes) {
        try {
            carService.deleteCar(id);
            redirectAttributes.addFlashAttribute("message", "Car deleted successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (CarNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/car";
    }
    @GetMapping ("/restore")
    public String restoreCar(Long id, RedirectAttributes redirectAttributes) {
        try {
            carService.restoreCar(id);
            redirectAttributes.addFlashAttribute("message", "Car restored successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (CarNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/car";
    }
}
