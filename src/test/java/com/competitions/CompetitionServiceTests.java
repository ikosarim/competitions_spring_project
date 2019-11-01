package com.competitions;

import com.competitions.entities.Captain;
import com.competitions.entities.Competition;
import com.competitions.entities.CompetitionLead;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class CompetitionServiceTests extends DataContainer {

    @Before
    public void initData() {
        CompetitionLead lead = leadService.createNewPerson(specialLead1Data,
                lead1PersonMap.get("personName"), lead1PersonMap.get("personSurName"), lead1PersonMap.get("personNickName"),
                lead1PassportMap.get("passportSeries"), lead1PassportMap.get("passportNumber"),
                lead1PassportMap.get("passportDateDay"), lead1PassportMap.get("passportDateMonth"),
                lead1PassportMap.get("passportDateYear"), lead1PhoneNums.toArray(String[]::new));
        leadService.addNewCompetition(lead, "Competition1", "Swim", "Gold");
        lead = leadService.getByNickName(lead1PersonMap.get("personNickName"));
        leadService.addNewCompetition(lead, "Competition2", "Run", "Silver");
    }

    @Test
    public void getAllCompetitionsTest() {
        Set<Competition> competitions = memberService.getAllCompetitions();
        assertEquals(2, competitions.size());
        competitions = captainService.getAllCompetitions();
        assertEquals(2, competitions.size());
        competitions = leadService.getAllCompetitions();
        assertEquals(2, competitions.size());
    }

    @Test
    public void getCompetitionByNameTest() {
        Competition competition = memberService.getCompetitionByName("Competition1");
        assertEquals("Swim", competition.getCompetitionDescription());
        assertEquals("Gold", competition.getCompetitionReward());
        competition = captainService.getCompetitionByName("Competition2");
        assertEquals("Run", competition.getCompetitionDescription());
        assertEquals("Silver", competition.getCompetitionReward());
        competition = leadService.getCompetitionByName("Competition2");
        assertEquals("Run", competition.getCompetitionDescription());
        assertEquals("Silver", competition.getCompetitionReward());
    }

    @Test
    public void getAllMembersOfCompetitionTest() {
        initCaptainData(specialCaptain1Data, captain1PersonMap, captain1PassportMap, captain1PhoneNums);
        try {
            checkGetAllMembersOfCompetition();
        } finally {
            destroyCaptainData();
        }
    }

    public void destroyCaptainData() {
        Captain captain = captainService.getByNickName("uncle_kolya");
        captainService.removePerson(captain);
    }

    private void checkGetAllMembersOfCompetition() {
        Captain captain = captainService.getByNickName("uncle_kolya");
        assert (captain != null);
        Competition competition1 = memberService.getCompetitionByName("Competition1");
        Competition competition2 = captainService.getCompetitionByName("Competition2");
        captainService.takePartInTheCompetition(captain, competition1);
        Set<Captain> captains1 = memberService.getAllTeamsForCompetition(competition1.getCompetitionName());
        Set<Captain> captains2 = memberService.getAllTeamsForCompetition(competition2.getCompetitionName());
        assertEquals(1, captains1.size());
        Captain captain1 = captains1.iterator().next();
        assertEquals("Nikolay", captain1.getPersonName());
        assertEquals("Maximov", captain1.getPersonSurname());
        assertEquals("uncle_kolya", captain1.getPersonNickName());
        assertEquals(0, captains2.size());
        assertEquals(captains1, captainService.getAllTeamsForCompetition(competition1.getCompetitionName()));
        assertEquals(captains1, leadService.getAllTeamsForCompetition(competition1.getCompetitionName()));
    }

    public void initCaptainData(Map<String, Object> specialCaptainData, Map<String, String> captainPersonMap,
                                Map<String, Integer> captainPassportMap, List<String> captainPhoneNums) {
        captainService.createNewPerson(specialCaptainData,
                captainPersonMap.get("personName"), captainPersonMap.get("personSurName"),
                captainPersonMap.get("personNickName"), captainPassportMap.get("passportSeries"),
                captainPassportMap.get("passportNumber"), captainPassportMap.get("passportDateDay"),
                captainPassportMap.get("passportDateMonth"), captainPassportMap.get("passportDateYear"),
                captainPhoneNums.toArray(String[]::new));
    }

    @After
    public void destroyData() {
        CompetitionLead lead = leadService.getByNickName("red");
        leadService.removePerson(lead);
    }
}
