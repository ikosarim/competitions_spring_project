package com.competitions.controllers;

import com.competitions.entities.Person;
import com.competitions.facade.CompetitionsFacade;
import com.competitions.widgets.RegistrationWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@ComponentScan(value = "com.competitions.facade")
public class RegistrationController {

    @Autowired
    CompetitionsFacade competitionsFacade;

    @GetMapping
    public String getRegistrationForm(Model model) {
        model.addAttribute("registrationWidget", new RegistrationWidget());
        return "/pages/registration";
    }

    @GetMapping(value = "/select_role")
    public String getRegistrationFormWithRole(Model model, @ModelAttribute RegistrationWidget registrationWidget) {
        model.addAttribute("registrationWidget", registrationWidget);
        return "/pages/registration";
    }

    @PostMapping
    public String createUser(Model model, @ModelAttribute RegistrationWidget registrationWidget) {
        Person person = competitionsFacade.createPerson(registrationWidget);
        if (person == null) {
            return "/pages/registration";
        }
        model.addAttribute("person", person);
        return "/pages/person_info";
    }
}
