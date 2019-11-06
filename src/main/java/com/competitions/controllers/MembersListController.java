package com.competitions.controllers;

import com.competitions.entities.Captain;
import com.competitions.entities.Member;
import com.competitions.entities.Person;
import com.competitions.facade.CompetitionsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller("/members_list")
@ComponentScan(value = "com.competitions.facade")
public class MembersListController {

    @Autowired
    CompetitionsFacade competitionsFacade;

    @GetMapping(value = "/{idPerson}")
    public String getMemberInfo(Model model, @ModelAttribute Person person, @PathVariable Integer idPerson) {
        model.addAttribute("person", competitionsFacade.findMemberFromOwnTeamById((Member) person, idPerson));
        return "/person_info";
    }

    @GetMapping
    public String deleteMemberFromTeam(Model model, @ModelAttribute Captain captain, @PathVariable Integer memberId) {
        competitionsFacade.deleteMemberFromCaptain(captain, memberId);
        return "/members_list";
    }
}
