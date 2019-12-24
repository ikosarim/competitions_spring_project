package com.competitions;

import com.competitions.init.AppInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompetitionsApplication
//        extends SpringBootServletInitializer
{

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{CompetitionsApplication.class, AppInitializer.class}, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(CompetitionsApplication.class);
//    }
}