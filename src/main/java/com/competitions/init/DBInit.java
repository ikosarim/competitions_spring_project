package com.competitions.init;

import com.competitions.entities.Authority;
import com.competitions.repos.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.competitions.entities.UserRoleEnum.*;

@Component
public class DBInit {

    @Autowired
    AuthorityRepository authorityRepository;

    @PostConstruct
    public void initDB() {
        List<Authority> authorities = authorityRepository.findAll();
        if (authorities.size() != 0 && authorities.size() != 3) {
            authorityRepository.deleteAll();
            fillTable();
        } else if (authorities.size() == 0) {
            fillTable();
        }
    }

    private void fillTable() {
        Authority member = Authority.builder().role(ROLE_MEMBER).build();
        authorityRepository.save(member);

        Authority captain = Authority.builder().role(ROLE_CAPTAIN).build();
        authorityRepository.save(captain);

        Authority competitionLead = Authority.builder().role(ROLE_LEAD).build();
        authorityRepository.save(competitionLead);
    }
}
