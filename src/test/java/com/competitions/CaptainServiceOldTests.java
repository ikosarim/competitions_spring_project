package com.competitions;

import com.competitions.entities.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.*;

public class CaptainServiceOldTests extends DataContainer {

    @Before
    public void initData() {
        initCaptain(specialCaptain1Data, captain1PersonMap, captain1PassportMap, captain1PhoneNums);
    }

    @Test
    public void getAllCaptainsTest() {
        initCaptain(specialCaptain2Data, captain2PersonMap, captain2PassportMap, captain2PhoneNums);
        try {
            List<Captain> captains = captainService.getAllCaptains();
            assertEquals(2, captains.size());
            assertTrue(captains.stream().allMatch(cap -> "uncle_kolya".equals(cap.getPersonNickName())
                    || "pol_pol".equals(cap.getPersonNickName())));
        } finally {
            destroyCaptain();
        }
    }

    @Test
    public void getCaptainByIdTest() {
        initCaptain(specialCaptain2Data, captain2PersonMap, captain2PassportMap, captain2PhoneNums);
        try {
            Captain captain = captainService.getByNickName("uncle_kolya");
            Captain captainById = captainService.getCaptainById(captain.getIdPerson());
            assertEquals(captain, captainById);

            captain = captainService.getByNickName("pol_pol");
            captainById = captainService.getCaptainById(captain.getIdPerson());
            assertEquals(captain, captainById);
        } finally {
            destroyCaptain();
        }
    }

    @Test
    public void changeTeamNameTest() {
        Captain captain = captainService.getByNickName("uncle_kolya");
        assertEquals("new_captain", captain.getCaptainTeamName());

        captainService.changeTeamName(captain, "second_captain");
        assertEquals("second_captain", captain.getCaptainTeamName());
    }

    @Test
    public void changeExperienceTest() {
        Captain captain = captainService.getByNickName("uncle_kolya");
        assertEquals(3.8, captain.getCaptainExperience());

        captainService.changeExperience(captain, 10.0);
        assertEquals(10.0, captain.getCaptainExperience());
    }

    @Test
    public void getAllRequestsToCaptainTest() {
        initMember(specialMember1Data, member1PersonMap, member1PassportMap, member1PhoneNums);
        initMember(specialMember2Data, member2PersonMap, member2PassportMap, member2PhoneNums);
        try {
            Member member1 = memberService.getByNickName(member1PersonMap.get("personNickName"));
            Member member2 = memberService.getByNickName(member2PersonMap.get("personNickName"));
            Captain captain = captainService.getByNickName(captain1PersonMap.get("personNickName"));
            memberService.createRequestForEnterInTeam(captain, member1, "Hi, mem1!");
            memberService.createRequestForEnterInTeam(captain, member2, "Hi, mem2!");
            Set<RequestForEnter> requestForEnters = captainService.getAllRequestsToCaptain(captain);
            assertEquals(2, requestForEnters.size());
            assertTrue(requestForEnters.stream().allMatch(rfe -> captain.equals(rfe.getCaptain())));
            assertTrue(requestForEnters.stream().allMatch(rfe -> member1.equals(rfe.getMember())
                    || member2.equals(rfe.getMember())));
        } finally {
            destroyMember(member1PersonMap.get("personNickName"));
            destroyMember(member2PersonMap.get("personNickName"));
        }
    }

    @Test
    public void showRequestToCaptainTest() {
        initMember(specialMember1Data, member1PersonMap, member1PassportMap, member1PhoneNums);
        try {
            Member member = memberService.getByNickName(member1PersonMap.get("personNickName"));
            Captain captain = captainService.getByNickName(captain1PersonMap.get("personNickName"));
            memberService.createRequestForEnterInTeam(captain, member, "Hi, mem!");
            assertEquals(1, captain.getRequestsForEnter().size());
            assertFalse(captain.getRequestsForEnter().iterator().next().isRequestStatus());
            captainService.showRequestToCaptain(captain, member);
            assertTrue(captain.getRequestsForEnter().iterator().next().isRequestStatus());
        } finally {
            destroyMember(member1PersonMap.get("personNickName"));
        }
    }

    @Test
    public void acceptMemberEnterToCaptainTest() {
        initMember(specialMember1Data, member1PersonMap, member1PassportMap, member1PhoneNums);
        try {
            Member member = memberService.getByNickName(member1PersonMap.get("personNickName"));
            Captain captain = captainService.getByNickName(captain1PersonMap.get("personNickName"));
            memberService.createRequestForEnterInTeam(captain, member, "Hi, mem!");
            assertEquals(1, captain.getRequestsForEnter().size());
            assertEquals(0, captain.getMembers());
            assertEquals(1, member.getRequestsForEnter().size());
            assert (member.getCaptain() == null);
            captainService.acceptMemberEnterToCaptain(captain, member);
            assertEquals(0, captain.getRequestsForEnter().size());
            assertEquals(1, captain.getMembers());
            assertEquals(0, member.getRequestsForEnter().size());
            assert (member.getCaptain() != null);
        } finally {
            destroyMember(member1PersonMap.get("personNickName"));
        }
    }

    @Test
    public void declineMemberEnterToCaptainTest() {
        initMember(specialMember1Data, member1PersonMap, member1PassportMap, member1PhoneNums);
        try {
            Member member = memberService.getByNickName(member1PersonMap.get("personNickName"));
            Captain captain = captainService.getByNickName(captain1PersonMap.get("personNickName"));
            memberService.createRequestForEnterInTeam(captain, member, "Hi, mem!");
            assertEquals(1, captain.getRequestsForEnter().size());
            assertEquals(0, captain.getMembers());
            assertEquals(1, member.getRequestsForEnter().size());
            assert (member.getCaptain() == null);
            captainService.declineMemberEnterToCaptain(captain, member);
            assertEquals(0, captain.getRequestsForEnter().size());
            assertEquals(0, captain.getMembers());
            assertEquals(0, member.getRequestsForEnter().size());
            assert (member.getCaptain() == null);
        } finally {
            destroyMember(member1PersonMap.get("personNickName"));
        }
    }

    @Test
    public void deleteMemberFromCaptainTest() {
        initMember(specialMember1Data, member1PersonMap, member1PassportMap, member1PhoneNums);
        try {
            Member member = memberService.getByNickName(member1PersonMap.get("personNickName"));
            Captain captain = captainService.getByNickName(captain1PersonMap.get("personNickName"));
            memberService.createRequestForEnterInTeam(captain, member, "Hi, mem!");
            captainService.acceptMemberEnterToCaptain(captain, member);
            assertEquals(captain, member.getCaptain());
            assertEquals(1, captain.getMembers().size());
            assertTrue(captain.getMembers().stream().allMatch(member::equals));
            captainService.deleteMemberFromCaptain(captain, member);
            assert (member.getCaptain() == null);
            assertEquals(0, captain.getMembers().size());
        } finally {
            destroyMember(member1PersonMap.get("personNickName"));
        }
    }

    @Test
    public void takePartInTheCompetitionTest() {
        initLead();
        try {
            CompetitionLead lead = leadService.getByNickName("red");
            Captain captain = captainService.getByNickName(captain1PersonMap.get("personNickName"));
            leadService.addNewCompetition(lead, "Competition1", "Run", "Gold");
            Competition competition = captainService.getCompetitionByName("Competition1");
            assertEquals(0, captain.getCompetitionSet().size());
            captainService.takePartInTheCompetition(captain, competition);
            assertEquals(0, captain.getCompetitionSet().size());
            assertEquals(competition, captain.getCompetitionSet().iterator().next());
        } finally {
            destroyLead();
        }
    }

    @Test
    public void leaveTheCompetitionTest() {
        initLead();
        try {
            CompetitionLead lead = leadService.getByNickName("red");
            Captain captain = captainService.getByNickName(captain1PersonMap.get("personNickName"));
            leadService.addNewCompetition(lead, "Competition1", "Run", "Gold");
            Competition competition = captainService.getCompetitionByName("Competition1");
            assertEquals(0, captain.getCompetitionSet().size());
            assertEquals(0, competition.getCaptainSet().size());
            captainService.takePartInTheCompetition(captain, competition);
            assertEquals(1, captain.getCompetitionSet().size());
            assertEquals(competition, captain.getCompetitionSet().iterator().next());
            assertEquals(1, competition.getCaptainSet().size());
            assertEquals(captain, competition.getCaptainSet().iterator().next());
            captainService.leaveTheCompetition(captain, competition);
            assertEquals(0, captain.getCompetitionSet().size());
            assertEquals(0, competition.getCaptainSet().size());
        } finally {
            destroyLead();
        }
    }

    @Test
    public void getAllCompetitionsForUserTest() {
        initLead();
        try {
            CompetitionLead lead = leadService.getByNickName("red");
            Captain captain = captainService.getByNickName(captain1PersonMap.get("personNickName"));
            leadService.addNewCompetition(lead, "Competition1", "Run", "Gold");
            leadService.addNewCompetition(lead, "Competition2", "Swim", "Silver");
            Competition competition1 = captainService.getCompetitionByName("Competition1");
            Competition competition2 = captainService.getCompetitionByName("Competition2");
            assertEquals(0, captain.getCompetitionSet().size());
            captainService.takePartInTheCompetition(captain, competition1);
            captainService.takePartInTheCompetition(captain, competition2);
            Set<Competition> competitions = captainService.getAllCompetitionsForUser(captain);
            assertEquals(2, competitions.size());
            assertEquals(2, captain.getCompetitionSet().size());
            assertTrue(competitions.stream().allMatch(comp -> competition1.equals(comp) || competition2.equals(comp)));
        } finally {
            destroyLead();
        }
    }

    private void initCaptain(Map<String, Object> specialCaptainData, Map<String, String> captainPersonMap,
                             Map<String, Integer> captainPassportMap, List<String> captainPhoneNums) {
        captainService.createNewPerson(specialCaptainData,
                captainPersonMap.get("personName"), captainPersonMap.get("personSurName"),
                captainPersonMap.get("personNickName"), captainPassportMap.get("passportSeries"),
                captainPassportMap.get("passportNumber"), captainPassportMap.get("passportDateDay"),
                captainPassportMap.get("passportDateMonth"), captainPassportMap.get("passportDateYear"),
                captainPhoneNums.toArray(String[]::new));
    }

    private void destroyCaptain() {
        Captain captain = captainService.getByNickName("pol_pol");
        captainService.removePerson(captain);
    }

    private void initMember(Map<String, Object> specialMember2Data, Map<String, String> member2PersonMap, Map<String, Integer> member2PassportMap, List<String> member2PhoneNums) {
        memberService.createNewPerson(specialMember2Data, member2PersonMap.get("personName"),
                member2PersonMap.get("personSurName"), member2PersonMap.get("personNickName"),
                member2PassportMap.get("passportSeries"), member2PassportMap.get("passportNumber"),
                member2PassportMap.get("passportDateDay"), member2PassportMap.get("passportDateMonth"),
                member2PassportMap.get("passportDateYear"), member2PhoneNums.toArray(String[]::new));
    }

    private void destroyMember(String nickName) {
        Member member = memberService.getByNickName(nickName);
        memberService.removePerson(member);
    }

    private CompetitionLead initLead() {
        return leadService.createNewPerson(specialLead1Data,
                lead1PersonMap.get("personName"), lead1PersonMap.get("personSurName"), lead1PersonMap.get("personNickName"),
                lead1PassportMap.get("passportSeries"), lead1PassportMap.get("passportNumber"),
                lead1PassportMap.get("passportDateDay"), lead1PassportMap.get("passportDateMonth"),
                lead1PassportMap.get("passportDateYear"), lead1PhoneNums.toArray(String[]::new));
    }

    private void destroyLead() {
        leadService.removePerson(leadService.getByNickName("red"));
    }

    @After
    public void destroyData() {
        Captain captain = captainService.getByNickName("uncle_kolya");
        captainService.removePerson(captain);
    }
}
