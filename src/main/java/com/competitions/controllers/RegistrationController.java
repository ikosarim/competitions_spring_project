package com.competitions.controllers;

import com.competitions.entities.Person;
import com.competitions.entities.UserRoleEnum;
import com.competitions.facade.CompetitionsFacade;
import com.competitions.widgets.RegistrationWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
@PreAuthorize("isAnonymous()")
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
    public String createUser(Model model, @RequestParam(value = "userInfoRole") UserRoleEnum role,
                             @ModelAttribute RegistrationWidget registrationWidget) {
        registrationWidget.setRole(role);
        Person person = competitionsFacade.createPerson(registrationWidget);
        model.addAttribute("person", person);
        return "redirect:/user_info";
    }
}
