package com.competitions.controllers;

import com.competitions.entities.Captain;
import com.competitions.facade.CompetitionsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@ComponentScan(value = "com.competitions.facade")
public class RequestsForEnterListController {

    @Autowired
    CompetitionsFacade competitionsFacade;

    @GetMapping(value = "/${idRequest}")
    public String showRequestToEnter(Model model, @ModelAttribute Captain captain, @PathVariable Integer requestId) {
        competitionsFacade.showRequestToCaptain(captain, requestId);
        return "/request_form";
    }
}
