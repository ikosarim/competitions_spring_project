package com.competitions.facade;

import com.competitions.entities.*;
import com.competitions.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    public Person createPerson(String memberDegree,
                               String captainTeamName, double captainExperience,
                               double leadExperience, String leadCertificate, String leadSpecialization,
                               String personName, String personSurname, String personNickName,
                               int passportSeries, int passportNumber,
                               int dayOfDate, int monthOfDate, int yearOfDate,
                               List<String> phoneNums) {
        String[] phones;
        if (phoneNums.isEmpty() || phoneNums == null) {
            phones = new String[0];
        } else {
            phones = new String[phoneNums.size()];
            int index = 0;
            for (String num : phoneNums) {
                phones[index] = num;
                index++;
            }
        }
        if (memberDegree != null) {
            return memberService.createNewPerson(memberDegree, personName, personSurname, personNickName,
                    passportSeries, passportNumber, dayOfDate, monthOfDate, yearOfDate, phones);
        } else if (captainTeamName != null) {
            return captainService.createNewPerson(captainTeamName, captainExperience, personName, personSurname, personNickName,
                    passportSeries, passportNumber, dayOfDate, monthOfDate, yearOfDate, phones);
        } else if (leadSpecialization != null) {
            return leadService.createNewPerson(leadExperience, leadCertificate, leadSpecialization,
                    personName, personSurname, personNickName, passportSeries, passportNumber,
                    dayOfDate, monthOfDate, yearOfDate, phones);
        }
        return null;
    }
}
