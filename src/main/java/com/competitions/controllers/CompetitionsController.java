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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/competitions_list")
@ComponentScan(value = "com.competitions.facade")
public class CompetitionsController {

    @Autowired
    CompetitionsFacade competitionsFacade;

    @GetMapping
    public String getCompetitionsList(Model uiModel) {
        uiModel.addAttribute("competitions", competitionsFacade.findAllCompetitions());
        return "/competitions_list";
    }

    @GetMapping(value = "/{idCompetition}")
    public String getCompetitionInfo(Model uiModel, @PathVariable Integer idCompetition) {
        uiModel.addAttribute("competition", competitionsFacade.getCompetitionInfo(idCompetition));
        return "/competition_info";
    }

    @GetMapping(value = "/{idCompetition}")
    public String takePartInTheCompetition(Model model, @ModelAttribute Captain captain, @PathVariable Integer competitionId) {
        competitionsFacade.takePartInTheCompetition(captain, competitionId);
        return "/competition_info";
    }

    @GetMapping(value = "/{idCompetition}")
    public String leaveTheCompetition(Model model, @ModelAttribute Captain captain, @PathVariable Integer competitionId) {
        competitionsFacade.leaveTheCompetition(captain, competitionId);
        return "/competition_info";
    }
}
