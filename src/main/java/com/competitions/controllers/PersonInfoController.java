package com.competitions.controllers;

import com.competitions.entities.*;
import com.competitions.facade.CompetitionsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/person_info")
@ComponentScan(value = "com.competitions.facade")
public class PersonInfoController {

    @Autowired
    CompetitionsFacade competitionsFacade;

    @PostMapping
    public String getPersonCardInfo(Model uiModel, @ModelAttribute("personNickName") String personNickName) {
        if (renderPersonCard(uiModel, personNickName)) return "/competitions_list";
        return "/person_info";
    }

    @PostMapping
    public String changePersonNickName(Model model, @ModelAttribute Person person, @ModelAttribute String nickName) {
        competitionsFacade.changePersonNickName(person, nickName);
        renderPersonCard(model, person.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String addNewPhone(Model model, @ModelAttribute Person person, @ModelAttribute String phoneNum) {
        competitionsFacade.addNewPhone(person, phoneNum);
        renderPersonCard(model, person.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String changePhone(Model model, @ModelAttribute Person person, @ModelAttribute Phone phone,
                              @ModelAttribute String phoneNum) {
        competitionsFacade.changePhone(person, phone, phoneNum);
        renderPersonCard(model, person.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String deletePhone(Model model, @ModelAttribute Person person, @ModelAttribute Phone phone,
                              @ModelAttribute String phoneNum) {
        competitionsFacade.deletePhone(person, phone);
        renderPersonCard(model, person.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String deletePhone(Model model, @ModelAttribute Person person) {
        competitionsFacade.deleteAllPhones(person);
        renderPersonCard(model, person.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String changeLeadExperience(Model model, @ModelAttribute Person person, @ModelAttribute double leadExperience) {
        CompetitionLead lead = (CompetitionLead) person;
        competitionsFacade.changeLeadExperience(lead, leadExperience);
        renderPersonCard(model, lead.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String changeLeadCertificate(Model model, @ModelAttribute Person person, @ModelAttribute String leadCertificate) {
        CompetitionLead lead = (CompetitionLead) person;
        competitionsFacade.changeLeadCertificate(lead, leadCertificate);
        renderPersonCard(model, lead.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String changeLeadSpecialization(Model model, @ModelAttribute Person person, @ModelAttribute String leadSpecialization) {
        CompetitionLead lead = (CompetitionLead) person;
        competitionsFacade.changeLeadSpecialization(lead, leadSpecialization);
        renderPersonCard(model, lead.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String changeMemberDegree(Model model, @ModelAttribute Person person, @ModelAttribute String memberDegree) {
        Member member = (Member) person;
        competitionsFacade.changeMemberDegree(member, memberDegree);
        renderPersonCard(model, member.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String changeTeamName(Model model, @ModelAttribute Person person, @ModelAttribute String newTeamName) {
        Captain captain = (Captain) person;
        competitionsFacade.changeTeamName(captain, newTeamName);
        renderPersonCard(model, captain.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String changeExperience(Model model, @ModelAttribute Person person, @ModelAttribute Double newExperience){
        Captain captain = (Captain) person;
        competitionsFacade.changeExperience(captain, newExperience);
        renderPersonCard(model, captain.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String deleteUser(Model model, @ModelAttribute Person person) {
        competitionsFacade.deleteUser(person);
        return "/competitions_list";
    }

    private boolean renderPersonCard(Model uiModel, String personNicName) {
        Person person = competitionsFacade.getPerson(personNicName);
        if (person == null) return true;
        uiModel.addAttribute("person", person);
        if (person instanceof Member) {
            uiModel.addAttribute("take_part_in_competitions", competitionsFacade.findAllCompetitionsForUser(person));
        } else if (person instanceof Captain) {
            uiModel.addAttribute("take_part_in_competitions", ((Captain) person).getCompetitionSet());
        } else if (person instanceof CompetitionLead) {
            uiModel.addAttribute("own_competitions", ((CompetitionLead) person).getCompetitions());
        }
        return false;
    }
}
