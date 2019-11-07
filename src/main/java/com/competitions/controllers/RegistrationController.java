package com.competitions.controllers;

import com.competitions.entities.Person;
import com.competitions.entities.UserRoleEnum;
import com.competitions.facade.CompetitionsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/registration")
@ComponentScan(value = "com.competitions.facade")
public class RegistrationController {

    @Autowired
    CompetitionsFacade competitionsFacade;

    @GetMapping
    public String getRegistrationForm() {
        return "/pages/registration";
    }

    @PostMapping
    public String createUser(Model model,
                             @ModelAttribute String personRole,
                             @ModelAttribute String memberDegree,
                             @ModelAttribute String captainTeamName, @ModelAttribute Double captainExperience,
                             @ModelAttribute Double leadExperience, @ModelAttribute String leadCertificate, @ModelAttribute String leadSpecialization,
                             @ModelAttribute String personName, @ModelAttribute String personSurname, @ModelAttribute String personNickName,
                             @ModelAttribute int passportSeries, @ModelAttribute int passportNumber,
                             @ModelAttribute int dayOfDate, @ModelAttribute int monthOfDate, @ModelAttribute int yearOfDate,
                             @ModelAttribute List<String> phoneNums) {
        Person person = competitionsFacade.createPerson(personRole, memberDegree, captainTeamName, captainExperience,
                leadExperience, leadCertificate, leadSpecialization, personName, personSurname, personNickName,
                passportSeries, passportNumber, dayOfDate, monthOfDate, yearOfDate, phoneNums);
        if (person == null) {
            return "/pages/registration";
        }
        model.addAttribute("person", person);
        return "/pages/person_info";
    }
}
