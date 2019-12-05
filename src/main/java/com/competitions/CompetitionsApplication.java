package com.competitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class CompetitionsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CompetitionsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CompetitionsApplication.class, args);
    }

    @Controller
    @RequestMapping("/")
    public class StartController {

        @GetMapping
        public String start() {
            return "/pages/index";
        }
    }
}