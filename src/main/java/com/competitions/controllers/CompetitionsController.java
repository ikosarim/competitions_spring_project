package com.competitions.controllers;

import com.competitions.facade.CompetitionsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/competitions_list")
@ComponentScan(value = "com.competitions.facade")
public class CompetitionsController {

    @Autowired
    CompetitionsFacade competitionsFacade;

    @GetMapping
    public String competitionsList(Model uiModel) {
        uiModel.addAttribute("competitions", competitionsFacade.findAllCompetitions());
        return "/competitions_list";
    }

    @GetMapping(value = "/{idCompetition}")
    public String competitionInfo(Model uiModel, @PathVariable Integer idCompetition) {
        uiModel.addAttribute("competition", competitionsFacade.getCompetitionInfo(idCompetition));
        return "/competition_info";
    }


}
