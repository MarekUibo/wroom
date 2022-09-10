package com.example.wroom.controllers;

import com.example.wroom.exceptions.PersonNotFoundException;
import com.example.wroom.models.Person;
import com.example.wroom.services.PersonService;
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
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public String showPersonListPage(Model model, @ModelAttribute("message") String message,
                                     @ModelAttribute("messageType") String messageType) {
        model.addAttribute("persons", personService.findAllPersons());
        return "person/list-person";
    }
    @GetMapping("/create")
    public String showCreatePersonPage(@ModelAttribute("person") Person person,
                                       @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        return "person/create-person";
    }
    @PostMapping
    public String createPerson(Person person, RedirectAttributes redirectAttributes) {
        try {
            Person searchPerson = personService.findPersonById(person.getId(UUID));
            redirectAttributes.addFlashAttribute("message",
                    String.format("Person(id=%d) already exists!"));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/person/create";
        } catch (PersonNotFoundException e) {
            personService.createPerson(person);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Person(id=%d) created successfully!", person.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/person/create";
        }
    }

    @GetMapping("/update")
    public String updatePerson(Person person, RedirectAttributes redirectAttributes) {
        try {
            personService.updatePerson(person);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Person(id=%d) updated successfully!", person.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/person";
        } catch (PersonNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    String.format("Person(id=%d) not found!", person.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/person";
        }
    }
    @GetMapping("/delete")
    public String deletePerson(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
        try {
            personService.deletePersonById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Person(id=%d) deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/person";
        } catch (PersonNotFoundException e) {
            return handlePersonNotFoundExceptionById(id, redirectAttributes);
        }
    }
    @GetMapping("/restore/{id}")
    public String restorePerson(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
        try {
            personService.restorePersonById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Person(id=%d) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/person";
        } catch (PersonNotFoundException e) {
            return handlePersonNotFoundExceptionById(id, redirectAttributes);
        }
    }

    //Private methods//
    private String handlePersonNotFoundExceptionById(UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                String.format("Person(id=%d) not found!", id));
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/person";
    }

}
