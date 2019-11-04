package com.competitions.controllers;

import com.competitions.entities.Member;
import com.competitions.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/competitions_list")
@ComponentScan(value = "com.competitions.services")
public class CompetitionsController {

    @Autowired
    MemberService<Member> memberService;

    @GetMapping
    public String competitionsList(Model uiModel) {
//        uiModel.addAttribute("competitions", memberService.getAllCompetitions());
        return "/competitions_list";
    }
}
