package com.example.wroom.controllers;

import com.example.wroom.exceptions.BranchNotFoundException;
import com.example.wroom.exceptions.CarNotFoundException;
import com.example.wroom.models.Branch;
import com.example.wroom.models.Car;
import com.example.wroom.models.CarBodyType;
import com.example.wroom.models.CarStatus;
import com.example.wroom.services.BranchService;
import com.example.wroom.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

/**
 * @author Marek Uibo
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private BranchService branchService;

    @GetMapping
    public String showCarListPage(Model model, @ModelAttribute("message") String message,
                                  @ModelAttribute("messageType") String messageType) {
        model.addAttribute("cars", carService.findAllCars());
        return "car/list-of-cars";
    }

    @GetMapping("/{id}")
    public String showCarViewPage(@PathVariable UUID id, Model model,
                                     RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("car", carService.findCarById(id));
            return "car/view-car";
        } catch (CarNotFoundException e) {
            return handleCarNotFoundExceptionById(id, redirectAttributes);
        }
    }

    @GetMapping ("/create")
    public String showCreateCarPage(@ModelAttribute("car") Car car,
                                    @ModelAttribute("message") String message,
                                    @ModelAttribute("messageType") String messageType,
                                    Model model) {
        model.addAttribute("carStatus", CarStatus.values());
        model.addAttribute("carBodyType", CarBodyType.values());
        return "car/create-car";
    }
    @PostMapping
    public String createCar(Car car, RedirectAttributes redirectAttributes) {
        try {
            Car searchCar = carService.findCarByRegistrationNumber(car.getRegistrationNumber());
            redirectAttributes.addFlashAttribute("message",
                    String.format("Car with registration number (%s) already exists!", searchCar.getRegistrationNumber()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/car/create";
        } catch (CarNotFoundException e) {
            carService.createCar(car);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Car with registration number (%s) created successfully!", car.getRegistrationNumber()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/car";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateCarPage(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes,
                                       @RequestParam(value = "car", required = false) Car car) {
        if (car == null) {
            try {
                model.addAttribute("car", carService.findCarById(id));
                model.addAttribute("carStatus", CarStatus.values());
                model.addAttribute("carBodyType", CarBodyType.values());
                model.addAttribute("homeBranch", branchService.findAllBranches());
            } catch (CarNotFoundException e) {
                return handleCarNotFoundExceptionById(id, redirectAttributes);
            }
        }

        return "car/update-car";
    }

    @PostMapping("/update")
    public String updateCar(Car car, RedirectAttributes redirectAttributes) {
            try {
                carService.updateCar(car);
                redirectAttributes.addFlashAttribute("message",
                        String.format("Car(id=%s) updated successfully!", car.getId()));
                redirectAttributes.addFlashAttribute("messageType", "success");
                return "redirect:/car";
            } catch (CarNotFoundException e) {
                return handleCarNotFoundExceptionById(car.getId(), redirectAttributes);
            }
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            carService.deleteCarById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Car(id=%s) deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/car";
        } catch (CarNotFoundException e) {
            return handleCarNotFoundExceptionById(id, redirectAttributes);
        }
    }
    @GetMapping ("/restore/{id}")
    public String restoreCar(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            carService.restoreCarById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Car(id=%s) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/car";
        } catch (CarNotFoundException e) {
            return handleCarNotFoundExceptionById(id, redirectAttributes);
    }
    }
    //Private methods//

    private String handleCarNotFoundExceptionById(UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                String.format("Car(id=%s) not found!", id));
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/car";
    }
}
