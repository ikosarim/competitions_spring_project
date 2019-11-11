package com.competitions.services;

import com.competitions.entities.UserInfo;
import com.competitions.principal.UserInfoPrincipal;
import com.competitions.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetails")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByLogin(login);
        if (userInfo == null) {
            throw new UsernameNotFoundException(login);
        }
        return new UserInfoPrincipal(userInfo);
    }
}
