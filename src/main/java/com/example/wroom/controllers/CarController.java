package com.example.wroom.controllers;

import com.example.wroom.exceptions.CarNotFoundException;
import com.example.wroom.models.Car;
import com.example.wroom.services.CarService;
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
            Car searchSchool = carService.findCarByCarRegistrationNumber(car.getRegistrationNumber());
            redirectAttributes.addFlashAttribute("message",
                    String.format("Car(id=%d) already exists!", searchSchool.getRegistrationNumber()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/car/create";
        } catch (CarNotFoundException e) {
            carService.createCar(car);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Car(id=%d) created successfully!", car.getRegistrationNumber()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/car/create";
        }
    }
    @PostMapping("/update")
    public String updateCar(Car car, RedirectAttributes redirectAttributes) {
            try {
                carService.updateCar(car);
                redirectAttributes.addFlashAttribute("message",
                        String.format("Car(id=%d) updated successfully!", car.getRegistrationNumber()));
                redirectAttributes.addFlashAttribute("messageType", "success");
                return "redirect:/car";
            } catch (CarNotFoundException e) {
                redirectAttributes.addFlashAttribute("message", e.getMessage());
                redirectAttributes.addFlashAttribute("messageType", "error");
                return "redirect:/car";
            }
    }

    @GetMapping("/delete")
    public String deleteCar(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            carService.deleteCarById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Car(id=%d) deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/car";
        } catch (CarNotFoundException e) {
            return handleCarNotFoundExceptionById(id, redirectAttributes);
        }
    }
    @GetMapping ("/restore")
    public String restoreCar(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            carService.restoreCarById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Car(id=%d) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/car";
        } catch (CarNotFoundException e) {
            return handleCarNotFoundExceptionById(id, redirectAttributes);
    }
    }
    //Private methods//

    private String handleCarNotFoundExceptionById(UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                String.format("Car(id=%d) not found!", id));
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/car";
    }
}
