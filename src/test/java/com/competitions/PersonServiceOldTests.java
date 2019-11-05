package com.competitions;

import com.competitions.entities.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonServiceOldTests extends DataContainer {

    @Before
    public void initData() {
//        memberService.createNewPerson(specialMember1Data, member1PersonMap.get("personName"),
//                member1PersonMap.get("personSurName"), member1PersonMap.get("personNickName"),
//                member1PassportMap.get("passportSeries"), member1PassportMap.get("passportNumber"),
//                member1PassportMap.get("passportDateDay"), member1PassportMap.get("passportDateMonth"),
//                member1PassportMap.get("passportDateYear"), member1PhoneNums.toArray(String[]::new));
//        captainService.createNewPerson(specialCaptain1Data,
//                captain1PersonMap.get("personName"), captain1PersonMap.get("personSurName"),
//                captain1PersonMap.get("personNickName"), captain1PassportMap.get("passportSeries"),
//                captain1PassportMap.get("passportNumber"), captain1PassportMap.get("passportDateDay"),
//                captain1PassportMap.get("passportDateMonth"), captain1PassportMap.get("passportDateYear"),
//                captain1PhoneNums.toArray(String[]::new));
//        leadService.createNewPerson(specialLead1Data,
//                lead1PersonMap.get("personName"), lead1PersonMap.get("personSurName"), lead1PersonMap.get("personNickName"),
//                lead1PassportMap.get("passportSeries"), lead1PassportMap.get("passportNumber"),
//                lead1PassportMap.get("passportDateDay"), lead1PassportMap.get("passportDateMonth"),
//                lead1PassportMap.get("passportDateYear"), lead1PhoneNums.toArray(String[]::new));
    }

    @Test
    public void getByNickNameTest() {
//        getAndCheckPersonData("super_oleg", memberService, member1PersonMap, member1PassportMap,
//                member1PhoneNums.toArray(String[]::new));
//        getAndCheckPersonData("uncle_kolya", captainService, captain1PersonMap, captain1PassportMap,
//                captain1PhoneNums.toArray(String[]::new));
//        getAndCheckPersonData("red", leadService, lead1PersonMap, lead1PassportMap,
//                lead1PhoneNums.toArray(String[]::new));
    }

    @Test
    public void changeNickNameTest() {
//        Member member = (Member) getAndCheckPersonData("super_oleg", memberService, member1PersonMap,
//                member1PassportMap, member1PhoneNums.toArray(String[]::new));
//        Captain captain = (Captain) getAndCheckPersonData("uncle_kolya", captainService, captain1PersonMap,
//                captain1PassportMap, captain1PhoneNums.toArray(String[]::new));
//        CompetitionLead lead = (CompetitionLead) getAndCheckPersonData("red", leadService, lead1PersonMap,
//                lead1PassportMap, lead1PhoneNums.toArray(String[]::new));

//        memberService.changeNickName(member, "member_nick_name");
//        captainService.changeNickName(captain, "captain_nick_name");
//        leadService.changeNickName(lead, "lead_nick_name");

        Map<String, String> modifiedmember1PersonMap = new HashMap<>(Map.copyOf(member1PersonMap));
        modifiedmember1PersonMap.put("personNickName", "member_nick_name");
        Map<String, String> modifiedcaptain1PersonMap = new HashMap<>(Map.copyOf(captain1PersonMap));
        modifiedcaptain1PersonMap.put("personNickName", "captain_nick_name");
        Map<String, String> modifiedlead1PersonMap = new HashMap<>(Map.copyOf(lead1PersonMap));
        modifiedlead1PersonMap.put("personNickName", "lead_nick_name");

//        checkPersonData(modifiedmember1PersonMap, member1PassportMap, member, member1PhoneNums.toArray(String[]::new));
//        checkPersonData(modifiedcaptain1PersonMap, captain1PassportMap, captain, captain1PhoneNums.toArray(String[]::new));
//        checkPersonData(modifiedlead1PersonMap, lead1PassportMap, lead, lead1PhoneNums.toArray(String[]::new));

//        memberService.changeNickName(member, "super_oleg");
//        captainService.changeNickName(captain, "uncle_kolya");
//        leadService.changeNickName(lead, "red");
    }

    @Test
    public void addNewPhoneTest() {
//        Member member = (Member) getAndCheckPersonData("super_oleg", memberService, member1PersonMap,
//                member1PassportMap, member1PhoneNums.toArray(String[]::new));
//        Captain captain = (Captain) getAndCheckPersonData("uncle_kolya", captainService, captain1PersonMap,
//                captain1PassportMap, captain1PhoneNums.toArray(String[]::new));
//        CompetitionLead lead = (CompetitionLead) getAndCheckPersonData("red", leadService, lead1PersonMap,
//                lead1PassportMap, lead1PhoneNums.toArray(String[]::new));

//        memberService.addNewPhone(member, "88001110077");
//        captainService.addNewPhone(captain, "88001110078");
//        leadService.addNewPhone(lead, "88001110079");

        List<String> addedmember1PhoneNums = new ArrayList<>(List.copyOf(member1PhoneNums));
        addedmember1PhoneNums.add("88001110077");
        List<String> addedcaptain1PhoneNums = new ArrayList<>(List.copyOf(captain1PhoneNums));
        addedcaptain1PhoneNums.add("88001110078");
        List<String> addedlead1PhoneNums = new ArrayList<>(List.copyOf(lead1PhoneNums));
        addedlead1PhoneNums.add("88001110079");

//        assertEquals(member.getPhone().size(), addedmember1PhoneNums.size());
//        assertEquals(captain.getPhone().size(), addedcaptain1PhoneNums.size());
//        assertEquals(lead.getPhone().size(), addedlead1PhoneNums.size());

//        checkPersonData(member1PersonMap, member1PassportMap, member, addedmember1PhoneNums.toArray(String[]::new));
//        checkPersonData(captain1PersonMap, captain1PassportMap, captain, addedcaptain1PhoneNums.toArray(String[]::new));
//        checkPersonData(lead1PersonMap, lead1PassportMap, lead, addedlead1PhoneNums.toArray(String[]::new));
    }

    @Test
    public void changePhoneTest() {
//        Member member = (Member) getAndCheckPersonData("super_oleg", memberService, member1PersonMap,
//                member1PassportMap, member1PhoneNums.toArray(String[]::new));
//        Captain captain = (Captain) getAndCheckPersonData("uncle_kolya", captainService, captain1PersonMap,
//                captain1PassportMap, captain1PhoneNums.toArray(String[]::new));
//        CompetitionLead lead = (CompetitionLead) getAndCheckPersonData("red", leadService, lead1PersonMap,
//                lead1PassportMap, lead1PhoneNums.toArray(String[]::new));

//        memberService.changePhone(member, member1PhoneNums.get(0), "02");
//        captainService.changePhone(captain, captain1PhoneNums.get(0), "03");
//        leadService.changePhone(lead, "800", "801");

        List<String> modifiedmember1PhoneNums = new ArrayList<>(List.copyOf(member1PhoneNums));
        modifiedmember1PhoneNums.remove(member1PhoneNums.get(0));
        modifiedmember1PhoneNums.add("02");
        List<String> modifiedcaptain1PhoneNums = new ArrayList<>(List.copyOf(captain1PhoneNums));
        modifiedcaptain1PhoneNums.set(0, "03");
        List<String> modifiedlead1PhoneNums = new ArrayList<>(List.copyOf(lead1PhoneNums));

//        assertEquals(member.getPhone().size(), modifiedmember1PhoneNums.size());
//        assertEquals(captain.getPhone().size(), modifiedcaptain1PhoneNums.size());
//        assertEquals(lead.getPhone().size(), modifiedlead1PhoneNums.size());

//        checkPersonData(member1PersonMap, member1PassportMap, member, modifiedmember1PhoneNums.toArray(String[]::new));
//        checkPersonData(captain1PersonMap, captain1PassportMap, captain, modifiedcaptain1PhoneNums.toArray(String[]::new));
//        checkPersonData(lead1PersonMap, lead1PassportMap, lead, modifiedlead1PhoneNums.toArray(String[]::new));
    }

    @Test
    public void deleteAllPhonesTest() {
//        Member member = (Member) getAndCheckPersonData("super_oleg", memberService, member1PersonMap,
//                member1PassportMap, member1PhoneNums.toArray(String[]::new));
//        Captain captain = (Captain) getAndCheckPersonData("uncle_kolya", captainService, captain1PersonMap,
//                captain1PassportMap, captain1PhoneNums.toArray(String[]::new));
//        CompetitionLead lead = (CompetitionLead) getAndCheckPersonData("red", leadService, lead1PersonMap,
//                lead1PassportMap, lead1PhoneNums.toArray(String[]::new));

//        memberService.deleteAllPhones(member);
//        captainService.deleteAllPhones(captain);
//        leadService.deleteAllPhones(lead);

//        assertEquals(member.getPhone().size(), 0);
//        assertEquals(captain.getPhone().size(), 0);
//        assertEquals(lead.getPhone().size(), 0);

//        checkPersonData(member1PersonMap, member1PassportMap, member);
//        checkPersonData(captain1PersonMap, captain1PassportMap, captain);
//        checkPersonData(lead1PersonMap, lead1PassportMap, lead);
    }

    @Test
    public void deletePhoneTest() {
//        Member member = (Member) getAndCheckPersonData("super_oleg", memberService, member1PersonMap,
//                member1PassportMap, member1PhoneNums.toArray(String[]::new));
//        Captain captain = (Captain) getAndCheckPersonData("uncle_kolya", captainService, captain1PersonMap,
//                captain1PassportMap, captain1PhoneNums.toArray(String[]::new));
//        CompetitionLead lead = (CompetitionLead) getAndCheckPersonData("red", leadService, lead1PersonMap,
//                lead1PassportMap, lead1PhoneNums.toArray(String[]::new));

//        memberService.deletePhone(member, member1PhoneNums.get(0));
//        captainService.deletePhone(captain, captain1PhoneNums.get(0));
//        leadService.deletePhone(lead, "800");

        List<String> removedmember1PhoneNums = new ArrayList<>(List.copyOf(member1PhoneNums));
        removedmember1PhoneNums.remove(0);
        List<String> removedcaptain1PhoneNums = new ArrayList<>(List.copyOf(captain1PhoneNums));
        removedcaptain1PhoneNums.remove(0);
        List<String> removedlead1PhoneNums = new ArrayList<>(List.copyOf(lead1PhoneNums));

//        assertEquals(member.getPhone().size(), removedmember1PhoneNums.size());
//        assertEquals(captain.getPhone().size(), removedcaptain1PhoneNums.size());
//        assertEquals(lead.getPhone().size(), removedlead1PhoneNums.size());

//        checkPersonData(member1PersonMap, member1PassportMap, member, removedmember1PhoneNums.toArray(String[]::new));
//        checkPersonData(captain1PersonMap, captain1PassportMap, captain, removedcaptain1PhoneNums.toArray(String[]::new));
//        checkPersonData(lead1PersonMap, lead1PassportMap, lead, removedlead1PhoneNums.toArray(String[]::new));
    }

    @Test
    public void removePersonTest() {
//        Member member = (Member) getAndCheckPersonData("super_oleg", memberService, member1PersonMap,
//                member1PassportMap, member1PhoneNums.toArray(String[]::new));
//        Captain captain = (Captain) getAndCheckPersonData("uncle_kolya", captainService, captain1PersonMap,
//                captain1PassportMap, captain1PhoneNums.toArray(String[]::new));
//        CompetitionLead lead = (CompetitionLead) getAndCheckPersonData("red", leadService, lead1PersonMap,
//                lead1PassportMap, lead1PhoneNums.toArray(String[]::new));

//        leadService.removePerson(lead);
//        memberService.removePerson(member);
//        captainService.removePerson(captain);

//        assert (leadService.getByNickName("red") == null);
//        assert (leadService.getByNickName("uncle_kolya") == null);
//        assert (leadService.getByNickName("super_oleg") == null);

        initData();
    }

//    public Person getAndCheckPersonData(String nickName, PersonServiceOld personServiceOld, Map<String, String> personMap,
//                                        Map<String, Integer> passportMap, String... phoneNums) {
//        Person person = personServiceOld.getByNickName(nickName);
//        checkPersonData(personMap, passportMap, person, phoneNums);
//        return person;
//    }

    private void checkPersonData(Map<String, String> personMap, Map<String, Integer> passportMap, Person person, String... phoneNums) {
        assertEquals(person.getPersonName(), personMap.get("personName"));
        assertEquals(person.getPersonSurname(), personMap.get("personSurName"));
        assertEquals(person.getPersonNickName(), personMap.get("personNickName"));
        Passport passport = person.getPassport();
        assertEquals(Integer.valueOf(passport.getPassportNumber()), passportMap.get("passportNumber"));
        assertEquals(Integer.valueOf(passport.getPassportSeries()), passportMap.get("passportSeries"));
        assertTrue(passport.getDateOfIssue().contains(passportMap.get("passportDateDay").toString()));
        assertTrue(passport.getDateOfIssue().contains(passportMap.get("passportDateMonth").toString()));
        assertTrue(passport.getDateOfIssue().contains(passportMap.get("passportDateYear").toString()));
        assertEquals(phoneNums.length, person.getPhone().size());
        assertEquals(phoneNums.length, Arrays.stream(phoneNums)
                .filter(p -> person.getPhone().contains(new Phone(p)))
                .count());
    }

    @After
    public void destroyData() {
//        CompetitionLead lead = leadService.getByNickName("red");
//        leadService.removePerson(lead);
//        Member member = memberService.getByNickName("super_oleg");
//        memberService.removePerson(member);
//        Captain captain = captainService.getByNickName("uncle_kolya");
//        captainService.removePerson(captain);
    }
}
