package com.competitions.init;

import com.competitions.entities.Authorities;
import com.competitions.repos.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.competitions.entities.UserRoleEnum.*;

@Component
public class DBInit {

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @PostConstruct
    public void initDB() {
        List<Authorities> authorities = authoritiesRepository.findAll();
        if (authorities.size() != 0 && authorities.size() != 3) {
            authoritiesRepository.deleteAll();
            fillTable();
        } else if (authorities.size() == 0) {
            fillTable();
        }
    }

    private void fillTable() {
        Authorities member = Authorities.builder().role(ROLE_MEMBER).build();
        authoritiesRepository.save(member);

        Authorities captain = Authorities.builder().role(ROLE_CAPTAIN).build();
        authoritiesRepository.save(captain);

        Authorities competitionLead = Authorities.builder().role(ROLE_LEAD).build();
        authoritiesRepository.save(competitionLead);
    }
}
