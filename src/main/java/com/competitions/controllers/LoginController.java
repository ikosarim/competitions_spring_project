package com.competitions.controllers;

import com.competitions.entities.Person;
import com.competitions.principal.PrincipalUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
@Slf4j
@PreAuthorize("isAnonymous()")
public class LoginController {

    @GetMapping
    public String login() {
        return "/pages/login";
    }

    @GetMapping(value = "/loginFailed")
    public String loginError(Model model) {
        log.info("Login attempt failed");
        model.addAttribute("error", "true");
        return "/pages/login";
    }
    @GetMapping(value = "/logout")
    public String logout(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return "redirect:/";
    }
    @PostMapping(value = "/postLogin")
    public String postLogin(Model model, HttpSession session) {
        log.info("postLogin()");
        // read principal out of security context and set it to session
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        Person loggedInUser = ((PrincipalUserDetails) authentication.getPrincipal()).getUserDetails();
        model.addAttribute("currentUser", loggedInUser.getPersonNickName());
        session.setAttribute("userId", loggedInUser.getIdPerson());
        return "redirect:/user_info";
    }
    private void validatePrinciple(Object principal) {
        if (!(principal instanceof PrincipalUserDetails)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
    }
}
