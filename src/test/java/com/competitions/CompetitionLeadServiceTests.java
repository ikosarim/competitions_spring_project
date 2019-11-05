package com.competitions;

import com.competitions.entities.Competition;
import com.competitions.entities.CompetitionLead;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CompetitionLeadServiceTests extends DataContainer {

    @Before
    public void initData() {
        initLead(specialLead1Data, lead1PersonMap, lead1PassportMap, lead1PhoneNums);
    }

    @Test
    public void getAllLeadsTest() {
        initLead(specialLead2Data, lead2PersonMap, lead2PassportMap, lead2PhoneNums);
        List<CompetitionLead> leads = leadService.getAllLeads();
        assertTrue(leads.stream().allMatch(lead -> "red".equals(lead.getPersonNickName())
                || "black".equals(lead.getPersonNickName())));
    }

    @Test
    public void getLeadByIdTest() {
        initLead(specialLead2Data, lead2PersonMap, lead2PassportMap, lead2PhoneNums);

//        CompetitionLead lead = leadService.getByNickName("red");
//        CompetitionLead leadById = leadService.getLeadById(lead.getIdPerson());
//        assert (leadById != null);
//        assertEquals("red", leadById.getPersonNickName());

//        lead = leadService.getByNickName("black");
//        leadById = leadService.getLeadById(lead.getIdPerson());
//        assert (leadById != null);
//        assertEquals("black", leadById.getPersonNickName());
    }

    @Test
    public void changeLeadExperienceTest() {
//        CompetitionLead lead = leadService.getByNickName("red");

//        leadService.changeLeadExperience(lead, 1.8);
//        assertEquals(1.8, lead.getLeadExperience());

//        lead = leadService.getByNickName("red");
//        assertEquals(1.8, lead.getLeadExperience());
    }

    @Test
    public void changeLeadCertificateTest() {
//        CompetitionLead lead = leadService.getByNickName("red");

//        leadService.changeLeadCertificate(lead, "None");
//        assertEquals("None", lead.getLeadCertificates());

//        lead = leadService.getByNickName("red");
//        assertEquals("None", lead.getLeadCertificates());
    }

    @Test
    public void changeLeadSpecializationTest() {
//        CompetitionLead lead = leadService.getByNickName("red");

//        leadService.changeLeadSpecialization(lead, "Fly");
//        assertEquals("Fly", lead.getLeadSpecialization());

//        lead = leadService.getByNickName("red");
//        assertEquals("Fly", lead.getLeadSpecialization());
    }

    @Test
    public void addNewCompetitionTest() {
//        CompetitionLead lead = leadService.getByNickName("red");

//        leadService.addNewCompetition(lead, "Competition1", "Run", "Gold");
//        Set<Competition> competitions = leadService.getAllCompetitions();

//        assertEquals(1, competitions.size());
//        Competition competition = competitions.iterator().next();

//        assertEquals("Competition1", competition.getCompetitionName());
//        assertEquals("Run", competition.getCompetitionDescription());
//        assertEquals("Gold", competition.getCompetitionReward());

//        assertEquals(1, lead.getCompetitions().size());
//        assertTrue(lead.getCompetitions().stream().allMatch(comp -> "Competition1".equals(comp.getCompetitionName())));
    }

    @Test
    public void changeCompetitionTest() {
//        CompetitionLead lead = leadService.getByNickName("red");

//        leadService.addNewCompetition(lead, "Competition1", "Run", "Gold");
//        assertEquals(1, lead.getCompetitions().size());
//        assertTrue(lead.getCompetitions().stream().allMatch(comp -> "Competition1".equals(comp.getCompetitionName())
//                && "Run".equals(comp.getCompetitionDescription())
//                && "Gold".equals(comp.getCompetitionReward())));

//        Competition competition = lead.getCompetitions().iterator().next();
//        leadService.changeCompetition(lead, competition.getCompetitionName(), "Swim", "Silver");

//        competition = leadService.getCompetitionByName("Competition1");
//        assertEquals("Swim", competition.getCompetitionDescription());
//        assertEquals("Silver", competition.getCompetitionReward());

//        assertEquals(1, lead.getCompetitions().size());
//        assertEquals(competition, lead.getCompetitions().iterator().next());
    }

    @Test
    public void deleteAllCompetitionsTest() {
//        CompetitionLead lead = leadService.getByNickName("red");

//        leadService.addNewCompetition(lead, "Competition1", "Run", "Gold");
//        leadService.addNewCompetition(lead, "Competition2", "Swim", "Silver");

//        assertEquals(2, lead.getCompetitions().size());

//        leadService.deleteAllCompetitions(lead);

//        assertEquals(0, lead.getCompetitions().size());
    }

    @Test
    public void deleteCompetitionTest() {
//        CompetitionLead lead = leadService.getByNickName("red");

//        leadService.addNewCompetition(lead, "Competition1", "Run", "Gold");
//        leadService.addNewCompetition(lead, "Competition2", "Swim", "Silver");

//        assertEquals(2, lead.getCompetitions().size());

//        leadService.deleteCompetition(lead, "Competition1");

//        assertEquals(1, lead.getCompetitions().size());
//        Competition competition = lead.getCompetitions().iterator().next();

//        assertEquals("Competition2", competition.getCompetitionName());
//        assertEquals("Swim", competition.getCompetitionDescription());
//        assertEquals("Silver", competition.getCompetitionReward());
    }

    private void initLead(Map<String, Object> specialLead1Data, Map<String, String> lead1PersonMap, Map<String, Integer> lead1PassportMap,
                          List<String> lead1PhoneNums) {
//        leadService.createNewPerson(specialLead1Data,
//                lead1PersonMap.get("personName"), lead1PersonMap.get("personSurName"), lead1PersonMap.get("personNickName"),
//                lead1PassportMap.get("passportSeries"), lead1PassportMap.get("passportNumber"),
//                lead1PassportMap.get("passportDateDay"), lead1PassportMap.get("passportDateMonth"),
//                lead1PassportMap.get("passportDateYear"), lead1PhoneNums.toArray(String[]::new));
    }

    @After
    public void destroyData() {
//        CompetitionLead lead = leadService.getByNickName("red");
//        leadService.removePerson(lead);
    }
}
