package com.competitions.controllers;

import com.competitions.facade.CompetitionsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@ComponentScan(value = "com.competitions.facade")
public class LeadListController {

    @Autowired
    CompetitionsFacade competitionsFacade;

    @GetMapping(value = "/${idLead}")
    public String getCaptainInfo(Model model, @PathVariable Integer idLead) {
        model.addAttribute("lead", competitionsFacade.getCaptainById(idLead));
        return "/person_info";
    }
}
