package com.competitions.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class StartController {

    private final Logger logger = LoggerFactory.getLogger(StartController.class);

    @GetMapping
    @ResponseBody
    public String start(Model uiModel) {
        logger.warn("Enter to start method of controller");
        return "/index";
    }
}
