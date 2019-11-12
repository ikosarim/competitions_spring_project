package com.competitions.facade;

import com.competitions.entities.*;
import com.competitions.services.*;
import com.competitions.widgets.RegistrationWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class CompetitionsFacade {

    @Autowired
    CompetitionService competitionService;
    @Autowired
    MemberService memberService;
    @Autowired
    CaptainService captainService;
    @Autowired
    LeadService leadService;
    @Autowired
    PersonService personService;

    public List<Competition> findAllCompetitions() {
        return competitionService.findAllCompetitions();
    }

    public Competition getCompetitionInfo(Integer competitionId) {
        return competitionService.findCompetitionById(competitionId);
    }

    public Person getPerson(String personNickName) {
        Integer personId = personService.getByNickName(personNickName);
        if (personId == null) {
            return null;
        }
        Member member = memberService.findMemberById(personId);
        if (member != null) return member;
        Captain captain = captainService.getCaptainById(personId);
        if (captain != null) return captain;
        CompetitionLead lead = leadService.getLeadById(personId);
        if (lead != null) return lead;
        return null;
    }

    public Set<Competition> findAllCompetitionsForUser(Person person) {
        return competitionService.findAllCompetitionsForUser(person.getIdPerson());
    }

    public Person changePersonNickName(Person person, String nickName) {
        return personService.changeNickName(person, nickName);
    }

    public Person addNewPhone(Person person, String phoneNum) {
        return personService.addNewPhone(person, phoneNum);
    }

    public Person changePhone(Person person, Phone phone, String phoneNum) {
        return personService.changePhone(person, phone.getPhoneNum(), phoneNum);
    }

    public Person deletePhone(Person person, Phone phone) {
        return personService.deletePhone(person, phone.getPhoneNum());
    }

    public Person deleteAllPhones(Person person) {
        return personService.deleteAllPhones(person);
    }

    public CompetitionLead changeLeadExperience(CompetitionLead lead, double experience) {
        return leadService.changeLeadExperience(lead, experience);
    }

    public CompetitionLead changeLeadCertificate(CompetitionLead lead, String leadCertificate) {
        return leadService.changeLeadCertificate(lead, leadCertificate);
    }

    public CompetitionLead changeLeadSpecialization(CompetitionLead lead, String leadSpecialization) {
        return leadService.changeLeadSpecialization(lead, leadSpecialization);
    }

    public Member changeMemberDegree(Member member, String memberDegree) {
        return memberService.changeMemberDegree(member, memberDegree);
    }

    public Captain changeTeamName(Captain captain, String newTeamName) {
        return captainService.changeTeamName(captain, newTeamName);
    }

    public Captain changeExperience(Captain captain, double newExperience) {
        return captainService.changeExperience(captain, newExperience);
    }

    public void deleteUser(Person person) {
        if (person instanceof Member) {
            memberService.removePerson((Member) person);
        } else if (person instanceof Captain) {
            captainService.removePerson((Captain) person);
        } else if (person instanceof CompetitionLead) {
            leadService.removePerson((CompetitionLead) person);
        }
    }

    public Person createPerson(RegistrationWidget widget) {
        String[] phones = createPhonesArray(widget.getPhoneNums());
        switch (widget.getRole()) {
            case MEMBER:
                return memberService.createNewPerson(widget.getLogin(), widget.getPassword(), widget.getRole(),
                        widget.getMemberDegree(),
                        widget.getPersonName(), widget.getPersonSurname(), widget.getPersonNickName(),
                        widget.getPassportSeries(), widget.getPassportNumber(),
                        widget.getDayOfIssue(), widget.getMonthOfIssue(), widget.getYearOfIssue(),
                        phones);
            case CAPTAIN:
                return captainService.createNewPerson(widget.getLogin(), widget.getPassword(), widget.getRole(),
                        widget.getCaptainTeamName(), widget.getCaptainExperience(),
                        widget.getPersonName(), widget.getPersonSurname(), widget.getPersonNickName(),
                        widget.getPassportSeries(), widget.getPassportNumber(),
                        widget.getDayOfIssue(), widget.getMonthOfIssue(), widget.getYearOfIssue(),
                        phones);
            case LEAD:
                return leadService.createNewPerson(widget.getLogin(), widget.getPassword(), widget.getRole(),
                        widget.getLeadExperience(), widget.getLeadCertificate(), widget.getLeadSpecialization(),
                        widget.getPersonName(), widget.getPersonSurname(), widget.getPersonNickName(),
                        widget.getPassportSeries(), widget.getPassportNumber(),
                        widget.getDayOfIssue(), widget.getMonthOfIssue(), widget.getYearOfIssue(),
                        phones);
            default:
                throw new IllegalArgumentException();
        }
    }

    private String[] createPhonesArray(List<String> phoneNums) {
        phoneNums = phoneNums.stream()
                .filter(num -> !"".equals(num))
                .collect(toList());
        String[] phones = new String[phoneNums.size()];
        int index = 0;
        for (String num : phoneNums) {
            phones[index] = num;
            index++;
        }
        return phones;
    }

    public List<Member> findAllMembersFromOwnTeam(Member member) {
        return memberService.findAllMembersFromOwnTeam(member);
    }

    public Member findMemberFromOwnTeamById(Member member, Integer memberId) {
        return memberService.findMemberFromOwnTeamById(member, memberId);
    }

    public Set<RequestForEnter> findAllRequestsForEnterOfMember(Member member) {
        return memberService.findAllRequestsForEnterOfMember(member);
    }

    public Member leaveTeam(Member member) {
        return memberService.leaveTeam(member);
    }

    public Member createRequestForEnterInTeam(Captain captain, Member member, String description) {
        return memberService.createRequestForEnterInTeam(captain, member, description);
    }

    public Member cancelRequestToEnterInTeam(Integer requestId, Member member) {
        return memberService.cancelRequestForEnter(requestId, member);
    }

    public List<Captain> findAllCaptains() {
        return captainService.getAllCaptains();
    }

    public Captain getCaptainById(Integer id) {
        return captainService.getCaptainById(id);
    }

    public Captain showRequestToCaptain(Captain captain, Integer requestId) {
        return captainService.showRequestToCaptain(captain, requestId);
    }

    public Captain acceptMemberEnterToCaptain(Captain captain, Integer requestId) {
        return captainService.acceptMemberEnterToCaptain(captain, requestId);
    }

    public Captain declineMemberEnterToCaptain(Captain captain, Integer requestId) {
        return captainService.declineMemberEnterToCaptain(captain, requestId);
    }

    public Captain deleteMemberFromCaptain(Captain captain, Integer memberId) {
        Member member = memberService.findMemberById(memberId);
        return captainService.deleteMemberFromCaptain(captain, member);
    }

    public Captain takePartInTheCompetition(Captain captain, Integer competitionId) {
        Competition competition = competitionService.findCompetitionById(competitionId);
        return captainService.takePartInTheCompetition(captain, competition);
    }

    public Captain leaveTheCompetition(Captain captain, Integer competitionId) {
        Competition competition = competitionService.findCompetitionById(competitionId);
        return captainService.leaveTheCompetition(captain, competition);
    }

    public List<CompetitionLead> findAllCompetitionLeads() {
        return leadService.getAllLeads();
    }

    public CompetitionLead getLeadById(Integer leadId) {
        return leadService.getLeadById(leadId);
    }

    public CompetitionLead addCompetition(CompetitionLead lead, String competitionName, String
            competitionDescription,
                                          String competitionReward) {
        return leadService.addNewCompetition(lead, competitionName, competitionDescription, competitionReward);
    }

    public CompetitionLead changeCompetition(CompetitionLead lead, String competitionName, String
            competitionDescription,
                                             String competitionReward) {
        return leadService.changeCompetition(lead, competitionName, competitionDescription, competitionReward);
    }

    public CompetitionLead deleteAllCompetitions(CompetitionLead lead) {
        return leadService.deleteAllCompetitions(lead);
    }

    public CompetitionLead deleteCompetition(CompetitionLead lead, Integer competitionId) {
        Competition competition = competitionService.findCompetitionById(competitionId);
        return leadService.deleteCompetition(lead, competition.getCompetitionName());
    }
}
