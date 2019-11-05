package com.competitions;

import com.competitions.entities.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class MemberServiceOldTests extends DataContainer {

    @Before
    public void initData() {
        initMember(specialMember1Data, member1PersonMap, member1PassportMap, member1PhoneNums);
        initCaptain(specialCaptain1Data, captain1PersonMap, captain1PassportMap, captain1PhoneNums);
    }

    @Test
    public void changeMemberDegreeTest() {
        Member member = memberService.getByNickName("super_oleg");
        memberService.changeMemberDegree(member, "new_member_degree");
        assertEquals("new_member_degree", member.getMemberDegree());
        member = memberService.getByNickName("super_oleg");
        assertEquals("new_member_degree", member.getMemberDegree());
    }

    @Test
    public void createRequestForEnterInTeamTest() {
        Captain captain = captainService.getByNickName("uncle_kolya");
        Member member = memberService.getByNickName("super_oleg");
        memberService.createRequestForEnterInTeam(captain, member, "first_request");
        assertEquals(1, captain.getRequestsForEnter().size());
        assertEquals(1, member.getRequestsForEnter().size());
        RequestForEnter capRequest = captain.getRequestsForEnter().iterator().next();
        assertEquals(captain, capRequest.getCaptain());
        assertEquals(member, capRequest.getMember());
        assertEquals("first_request", capRequest.getRequestDescription());
        RequestForEnter memRequest = member.getRequestsForEnter().iterator().next();
        assertEquals(captain, memRequest.getCaptain());
        assertEquals(member, memRequest.getMember());
        assertEquals("first_request", memRequest.getRequestDescription());
    }

    @Test(expected = Exception.class)
    public void createDoubleRequestForEnter() {
        Captain captain = captainService.getByNickName("uncle_kolya");
        Member member = memberService.getByNickName("super_oleg");
        memberService.createRequestForEnterInTeam(captain, member, "first_request");
        memberService.createRequestForEnterInTeam(captain, member, "second_request");
    }

    @Test(expected = Exception.class) // ??????????????????????
    public void createRequestWhileInTeam() {
        Captain captain = captainService.getByNickName("uncle_kolya");
        Member member = memberService.getByNickName("super_oleg");
        memberService.createRequestForEnterInTeam(captain, member, "first_request");
        captainService.acceptMemberEnterToCaptain(captain, member);

        initCaptain(specialCaptain2Data, captain2PersonMap, captain2PassportMap, captain2PhoneNums);
        try {
            Captain secondCaptain = captainService.getByNickName("pol_pol");
            memberService.createRequestForEnterInTeam(secondCaptain, member, "first_request");
        } finally {
            destroyCaptain();
        }
    }

    @Test
    public void cancelRequestForEnterTest() {
        Captain captain = captainService.getByNickName("uncle_kolya");
        Member member = memberService.getByNickName("super_oleg");
        memberService.createRequestForEnterInTeam(captain, member, "first_request");
        assertEquals(1, captain.getRequestsForEnter().size());
        assertEquals(1, member.getRequestsForEnter().size());
        memberService.cancelRequestForEnter(captain, member);
        assertEquals(0, captain.getRequestsForEnter().size());
        assertEquals(0, member.getRequestsForEnter().size());
    }

    @Test
    public void getAllRequestsForEnterOfMemberTest() {
        initCaptain(specialCaptain2Data, captain2PersonMap, captain2PassportMap, captain2PhoneNums);
        try {
            Captain captain1 = captainService.getByNickName("uncle_kolya");
            Captain captain2 = captainService.getByNickName("pol_pol");
            Member member = memberService.getByNickName("super_oleg");
            memberService.createRequestForEnterInTeam(captain1, member, "first_request");
            memberService.createRequestForEnterInTeam(captain2, member, "second_request");

            Set<RequestForEnter> requestForEnters = memberService.getAllRequestsForEnterOfMember(member);
            requestForEnters.forEach(rfe -> {
                assert ("first_request".equals(rfe.getRequestDescription())
                        || "second_request".equals(rfe.getRequestDescription()));
            });
        } finally {
            destroyCaptain();
        }
    }

    @Test
    public void getAllMembersFromOwnTeamTest() {
        initMember(specialMember2Data, member2PersonMap, member2PassportMap, member2PhoneNums);
        try {
            Member member1 = memberService.getByNickName("new_member");
            Member member2 = memberService.getByNickName("second_member");
            Captain captain = captainService.getByNickName("new_captain");
            memberService.createRequestForEnterInTeam(captain, member1, "first_request");
            memberService.createRequestForEnterInTeam(captain, member2, "second_request");
            captainService.acceptMemberEnterToCaptain(captain, member1);
            captainService.acceptMemberEnterToCaptain(captain, member2);

            List<Member> members = memberService.getAllMembersFromOwnTeam(member1);
            assertEquals(2, members.size());
            assertTrue(members.stream().allMatch(mem -> member1PersonMap.get("personNickName").equals(mem.getPersonNickName())
                    || member2PersonMap.get("personNickName").equals(mem.getPersonNickName())));

            members = memberService.getAllMembersFromOwnTeam(member2);
            assertEquals(2, members.size());
            assertTrue(members.stream().allMatch(mem -> member1PersonMap.get("personNickName").equals(mem.getPersonNickName())
                    || member2PersonMap.get("personNickName").equals(mem.getPersonNickName())));
        } finally {
            destroyMember();
        }
    }

    @Test
    public void getMemberFromOwnTeamByIdTest() {
        initMember(specialMember2Data, member2PersonMap, member2PassportMap, member2PhoneNums);
        try {
            Member member1 = memberService.getByNickName("new_member");
            Member member2 = memberService.getByNickName("second_member");
            Captain captain = captainService.getByNickName("new_captain");
            memberService.createRequestForEnterInTeam(captain, member1, "first_request");
            memberService.createRequestForEnterInTeam(captain, member2, "second_request");
            captainService.acceptMemberEnterToCaptain(captain, member1);
            captainService.acceptMemberEnterToCaptain(captain, member2);

            Member member = memberService.getMemberFromOwnTeamById(member1, member2.getIdPerson());
            assert (member != null);
            assertEquals(member2PersonMap.get("personNickName"), member2.getPersonNickName());
        } finally {
            destroyMember();
        }
    }

    @Test
    public void canNotGetMemberFromAnotherTeamByIdTest() {
        initMember(specialMember2Data, member2PersonMap, member2PassportMap, member2PhoneNums);
        try {
            Member member1 = memberService.getByNickName("new_member");
            Member member2 = memberService.getByNickName("second_member");
            Captain captain = captainService.getByNickName("new_captain");
            memberService.createRequestForEnterInTeam(captain, member1, "first_request");
            captainService.acceptMemberEnterToCaptain(captain, member1);

            Member member = memberService.getMemberFromOwnTeamById(member1, member2.getIdPerson());
            assert (member == null);
        } finally {
            destroyMember();
        }
    }

    @Test
    public void getAllCompetitionsForUserTest() {
        Member member = memberService.getByNickName("new_member");
        Captain captain = captainService.getByNickName("new_captain");
        memberService.createRequestForEnterInTeam(captain, member, "Hello!");
        captainService.acceptMemberEnterToCaptain(captain, member);
        CompetitionLead lead = initLead();
        try {
            leadService.addNewCompetition(lead, "Competition1", "Run", "Gold");
            leadService.addNewCompetition(lead, "Competition2", "Swim", "Gold");
            leadService.addNewCompetition(lead, "Competition1", "Jump", "Gold");
            Competition competition1 = memberService.getCompetitionByName("Competition1");
            Competition competition2 = memberService.getCompetitionByName("Competition2");
            Competition competition3 = memberService.getCompetitionByName("Competition3");
            captainService.takePartInTheCompetition(captain, competition1);
            captainService.takePartInTheCompetition(captain, competition2);

            Set<Competition> competitions = memberService.findAllCompetitionsForUser(member.getPersonNickName());
            assertEquals(2, competitions.size());
            assertTrue(competitions.stream().allMatch(comp -> "Competition1".equals(comp.getCompetitionName())
                    || "Competition2".equals(comp.getCompetitionName())));
            assertTrue(competitions.stream().noneMatch(comp -> "Competition3".equals(comp.getCompetitionName())));
        } finally {
            destroyLead();
        }
    }

    @Test
    public void leaveTeamTest() {
        Member member = memberService.getByNickName("new_member");
        Captain captain = captainService.getByNickName("new_captain");
        memberService.createRequestForEnterInTeam(captain, member, "Hello!");
        captainService.acceptMemberEnterToCaptain(captain, member);

        assertEquals(1, captain.getMembers().size());
        assertEquals("new_captain", member.getCaptain().getPersonNickName());
        assertTrue(captain.getMembers().stream().allMatch(mem -> "new_member".equals(mem.getPersonNickName())));

        memberService.leaveTeam(member);

        assertEquals(0, captain.getMembers().size());
        assert (member.getCaptain() == null);
    }

    private void initMember(Map<String, Object> specialMemberData, Map<String, String> memberPersonMap,
                            Map<String, Integer> memberPassportMap, List<String> memberPhoneNums) {
        memberService.createNewPerson(specialMemberData, memberPersonMap.get("personName"),
                memberPersonMap.get("personSurName"), memberPersonMap.get("personNickName"),
                memberPassportMap.get("passportSeries"), memberPassportMap.get("passportNumber"),
                memberPassportMap.get("passportDateDay"), memberPassportMap.get("passportDateMonth"),
                memberPassportMap.get("passportDateYear"), memberPhoneNums.toArray(String[]::new));
    }

    private void destroyMember() {
        Member member = memberService.getByNickName("super_petr");
        memberService.removePerson(member);
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
        Member member = memberService.getByNickName("super_oleg");
        memberService.removePerson(member);
        assertNull(memberService.getByNickName("super_oleg"));
        Captain captain = captainService.getByNickName("uncle_kolya");
        captainService.removePerson(captain);
        assertNull(captainService.getByNickName("uncle_kolya"));
    }
}
