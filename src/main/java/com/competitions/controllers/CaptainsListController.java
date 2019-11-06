package com.competitions.controllers;

import com.competitions.facade.CompetitionsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller("/captains_list")
@ComponentScan(value = "com.competitions.facade")
public class CaptainsListController {

    @Autowired
    CompetitionsFacade competitionsFacade;

//    @GetMapping
//    @GetMapping(value = "/${idCaptain}")
    public String getCaptainInfo(Model model, @PathVariable Integer idCaptain) {
        model.addAttribute("captain", competitionsFacade.getCaptainById(idCaptain));
        return "/person_info";
    }
}
