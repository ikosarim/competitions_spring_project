package com.competitions.controllers;

import com.competitions.entities.*;
import com.competitions.facade.CompetitionsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String changeExperience(Model model, @ModelAttribute Person person, @ModelAttribute Double newExperience) {
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

    @GetMapping
    public String getAllUsersFromOwnTeam(Model model, @ModelAttribute Person person) {
        if (person instanceof Member) {
            model.addAttribute("person_type", "member");
            model.addAttribute("partners", competitionsFacade.findAllMembersFromOwnTeam((Member) person));
        } else if (person instanceof Captain) {
            model.addAttribute("person_type", "captain");
            model.addAttribute("partners", ((Captain) person).getMembers());
        }
        return "members_list";
    }

    @GetMapping
    public String findAllRequestsForEnterOfMember(Model model, @ModelAttribute Person person) {
        Member member = (Member) person;
        model.addAttribute("requests", competitionsFacade.findAllRequestsForEnterOfMember(member));
        return "/requests_list";
    }

    @GetMapping
    public String leaveTeam(Model model, @ModelAttribute Person person) {
        Member member = (Member) person;
        competitionsFacade.leaveTeam(member);
        return "/person_info";
    }

    @GetMapping
    public String findAllCaptains(Model model) {
        model.addAttribute("captains", competitionsFacade.findAllCaptains());
        return "/captains_list";
    }

    @GetMapping
    public String showAllRequestsToCaptain(Model model, @ModelAttribute Captain captain, @ModelAttribute Member member) {
        model.addAttribute("requests", captain.getRequestsForEnter());
        return "/requests_list";
    }

    @GetMapping
    public String findAllCompetitionLeads(Model model) {
        model.addAttribute("leads", competitionsFacade.findAllCompetitionLeads());
        return "/leads_list";
    }

    @PostMapping
    public String addCompetition(Model model, @ModelAttribute Person person, @ModelAttribute String competitionName,
                                 @ModelAttribute String competitionDescription, @ModelAttribute String competitionReward) {
        CompetitionLead lead = (CompetitionLead) person;
        competitionsFacade.addCompetition(lead, competitionName, competitionDescription, competitionReward);
        renderPersonCard(model, lead.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String changeCompetition(Model model, @ModelAttribute Person person, @ModelAttribute String competitionName,
                                    @ModelAttribute String competitionDescription, @ModelAttribute String competitionReward) {
        CompetitionLead lead = (CompetitionLead) person;
        competitionsFacade.changeCompetition(lead, competitionName, competitionDescription, competitionReward);
        renderPersonCard(model, lead.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String deleteAllCompetitions(Model model, @ModelAttribute Person person) {
        CompetitionLead lead = (CompetitionLead) person;
        competitionsFacade.deleteAllCompetitions(lead);
        renderPersonCard(model, lead.getPersonNickName());
        return "/person_info";
    }

    @PostMapping
    public String deleteCompetition(Model model, @ModelAttribute Person person, @PathVariable Integer competitionId) {
        CompetitionLead lead = (CompetitionLead) person;
        competitionsFacade.deleteCompetition(lead, competitionId);
        renderPersonCard(model, lead.getPersonNickName());
        return "/person_info";
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
