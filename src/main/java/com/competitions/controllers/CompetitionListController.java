package com.competitions.controllers;

import com.competitions.entities.Captain;
import com.competitions.entities.Competition;
import com.competitions.facade.CompetitionsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/competitions_list")
@ComponentScan(value = "com.competitions.facade")
public class CompetitionListController {

    @Autowired
    CompetitionsFacade competitionsFacade;

    @GetMapping
    public String getCompetitionsList(Model uiModel) {
//        uiModel.addAttribute("competitions", competitionsFacade.findAllCompetitions());
        ArrayList<Competition> comp = new ArrayList<>(){{
            add(Competition.builder().competitionName("1").competitionDescription("adj").competitionReward("jahsdg").build());
            add(Competition.builder().competitionName("2").competitionDescription("asdfD").competitionReward("asdfwea").build());
            add(Competition.builder().competitionName("3").competitionDescription("cwadasd").competitionReward("asdfsad").build());
        }};
//        uiModel.addAttribute("competitions", competitionsFacade.findAllCompetitions());
        uiModel.addAttribute("competitions", comp);
        return "/pages/competitions_list";
    }

    @GetMapping(params = {"infoOfCompetition"})
    public String getCompetitionInfo(Model uiModel, @ModelAttribute String competitionName) {
//        uiModel.addAttribute("competition", competitionsFacade.getCompetitionInfo(idCompetition));
        System.out.println();
        return "/pages/competition_info";
    }

//    @GetMapping
//    @GetMapping(value = "/{idCompetition}")
    public String takePartInTheCompetition(Model model, @ModelAttribute Captain captain, @PathVariable Integer competitionId) {
        competitionsFacade.takePartInTheCompetition(captain, competitionId);
        return "/competition_info";
    }

//    @GetMapping
//    @GetMapping(value = "/{idCompetition}")
    public String leaveTheCompetition(Model model, @ModelAttribute Captain captain, @PathVariable Integer competitionId) {
        competitionsFacade.leaveTheCompetition(captain, competitionId);
        return "/competition_info";
    }
}
