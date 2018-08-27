package com.solstice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MentortreeServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MentortreeServiceApplication.class, args);

//        MentorTreeRepository mentorTreeRepository = context.getBean(MentorTreeRepository.class);
//
//        mentorTreeRepository.save(new MentorTree((long)6,(long)11,(long)1));
//        mentorTreeRepository.save(new MentorTree((long)7,(long)11,(long)1));
    }
}
