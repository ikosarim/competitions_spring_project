package com.competitions.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) {
//        try {
//            auth.inMemoryAuthentication()
//                    .withUser("user")
//                    .password("user")
//                    .roles("USER");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*")
                .permitAll()
                .and()
        .formLogin()
        .loginPage("/login.html")
        .failureUrl("/asdjkfhal")
        .and()
        .logout()
        .logoutSuccessUrl("/index.html");
    }

    // TODO: 05.11.2019 Реализовать корректно
}