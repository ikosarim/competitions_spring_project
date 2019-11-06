package com.competitions.controllers;

import com.competitions.entities.Captain;
import com.competitions.entities.Member;
import com.competitions.facade.CompetitionsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/request_form")
@ComponentScan(value = "com.competitions.facade")
public class RequestForEnterFormController {

    @Autowired
    CompetitionsFacade competitionsFacade;

//    @PostMapping
    public String createRequestToTeam(Model model, @ModelAttribute Captain captain, @ModelAttribute Member member,
                                      @ModelAttribute String description) {
        model.addAttribute("person_type", captain);
        return "/person_info";
    }

//    @GetMapping
    public String cancelRequestToEnterInTeam(Model model, @PathVariable Integer requestId, @ModelAttribute Member member) {
        competitionsFacade.cancelRequestToEnterInTeam(requestId, member);
        return "/person_info";
    }

//    @GetMapping
    public String acceptMemberEnterToCaptain(Model model, @ModelAttribute Captain captain, @PathVariable Integer requestId) {
        competitionsFacade.acceptMemberEnterToCaptain(captain, requestId);
        return "/person_info";
    }

//    @GetMapping
    public String declineMemberEnterToCaptain(@ModelAttribute Captain captain, @PathVariable Integer requestId) {
        competitionsFacade.declineMemberEnterToCaptain(captain, requestId);
        return "/person_info";
    }
}
