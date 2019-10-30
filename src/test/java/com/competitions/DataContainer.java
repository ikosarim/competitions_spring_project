package com.competitions;

import com.competitions.config.DatabaseConfig;
import com.competitions.entities.Captain;
import com.competitions.entities.CompetitionLead;
import com.competitions.entities.Member;
import com.competitions.services.CaptainService;
import com.competitions.services.LeadService;
import com.competitions.services.MemberService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
public abstract class DataContainer {

    @Autowired
    CaptainService<Captain> captainService;
    @Autowired
    MemberService<Member> memberService;
    @Autowired
    LeadService<CompetitionLead> leadService;


    Map<String, Object> specialMember1Data = new HashMap<>() {{
        put("memberDegree", "new_member");
    }};
    ;
    Map<String, String> member1PersonMap = new HashMap<>() {{
        put("personName", "Oleg");
        put("personSurName", "Olegin");
        put("personNickName", "super_oleg");
    }};
    Map<String, Integer> member1PassportMap = new HashMap<>() {{
        put("passportNumber", 4011);
        put("passportSeries", 147854);
        put("passportDateDay", 5);
        put("passportDateMonth", 4);
        put("passportDateYear", 1990);
    }};
    List<String> member1PhoneNums = new ArrayList<>() {{
        add("89211556235");
        add("89021809870");
    }};

    Map<String, Object> specialMember2Data = new HashMap<>() {{
        put("memberDegree", "second_member");
    }};
    Map<String, String> member2PersonMap = new HashMap<>() {{
        put("personName", "Petr");
        put("personSurName", "Petrov");
        put("personNickName", "super_petr");
    }};
    Map<String, Integer> member2PassportMap = new HashMap<>() {{
        put("passportNumber", 4001);
        put("passportSeries", 147114);
        put("passportDateDay", 12);
        put("passportDateMonth", 12);
        put("passportDateYear", 1995);
    }};
    List<String> member2PhoneNums = new ArrayList<>() {{
        add("89210576235");
        add("89021339870");
    }};

    Map<String, Object> specialCaptain1Data = new HashMap<>() {{
        put("captainTeamName", "new_captain");
        put("captainExperience", 3.8);
    }};
    Map<String, String> captain1PersonMap = new HashMap<>() {{
        put("personName", "Nikolay");
        put("personSurName", "Maximov");
        put("personNickName", "uncle_kolya");
    }};
    Map<String, Integer> captain1PassportMap = new HashMap<>() {{
        put("passportNumber", 4155);
        put("passportSeries", 794651);
        put("passportDateDay", 7);
        put("passportDateMonth", 10);
        put("passportDateYear", 1990);
    }};
    List<String> captain1PhoneNums = new ArrayList<>() {{
        add("89210871213");
    }};

    Map<String, Object> specialCaptain2Data = new HashMap<>() {{
        put("captainTeamName", "second_captain");
        put("captainExperience", 2.8);
    }};
    Map<String, String> captain2PersonMap = new HashMap<>() {{
        put("personName", "Alexey");
        put("personSurName", "Polovin");
        put("personNickName", "pol_pol");
    }};
    Map<String, Integer> captain2PassportMap = new HashMap<>() {{
        put("passportNumber", 4156);
        put("passportSeries", 794652);
        put("passportDateDay", 8);
        put("passportDateMonth", 11);
        put("passportDateYear", 1991);
    }};
    List<String> captain2PhoneNums = new ArrayList<>() {{
        add("89210871214");
    }};

    Map<String, Object> specialLead1Data = new HashMap<>() {{
        put("leadSpecialization", "run");
        put("leadExperience", 2.0);
        put("leadCertificates", "many");
    }};
    Map<String, String> lead1PersonMap = new HashMap<>() {{
        put("personName", "Andrey");
        put("personSurName", "Krasnov");
        put("personNickName", "red");
    }};
    Map<String, Integer> lead1PassportMap = new HashMap<>() {{
        put("passportNumber", 4266);
        put("passportSeries", 813346);
        put("passportDateDay", 6);
        put("passportDateMonth", 8);
        put("passportDateYear", 1978);
    }};
    List<String> lead1PhoneNums = new ArrayList<>();

    Map<String, Object> specialLead2Data = new HashMap<>() {{
        put("leadSpecialization", "swim");
        put("leadExperience", 3.0);
        put("leadCertificates", "some");
    }};
    Map<String, String> lead2PersonMap = new HashMap<>() {{
        put("personName", "Ivan");
        put("personSurName", "Blacked");
        put("personNickName", "black");
    }};
    Map<String, Integer> lead2PassportMap = new HashMap<>() {{
        put("passportNumber", 4268);
        put("passportSeries", 813996);
        put("passportDateDay", 1);
        put("passportDateMonth", 2);
        put("passportDateYear", 1988);
    }};
    List<String> lead2PhoneNums = new ArrayList<>() {{
        add("88005000500");
    }};
}
