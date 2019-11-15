package com.competitions.security;

import com.competitions.entities.Person;
import com.competitions.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component(value = "customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByPersonNickName(username);
        if (person == null) {
            throw new UsernameNotFoundException(username);
        }
        return new PersonPrincipal(person);
    }
}
