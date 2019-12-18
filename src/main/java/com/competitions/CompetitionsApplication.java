package com.competitions;

import com.competitions.init.AppInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompetitionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{CompetitionsApplication.class, AppInitializer.class}, args);
    }
}