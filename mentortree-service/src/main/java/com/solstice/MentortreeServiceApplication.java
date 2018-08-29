package com.solstice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MentortreeServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MentortreeServiceApplication.class, args);

   /*     MentorTreeRepository mentorTreeRepository = context.getBean(MentorTreeRepository.class);

        mentorTreeRepository.save(new MentorTree(1L, 2L, 2L));
        mentorTreeRepository.save(new MentorTree(2L, null, null));
        mentorTreeRepository.save(new MentorTree(3L, 1L, 2L));
        mentorTreeRepository.save(new MentorTree(4L, 1L, 2L));
        mentorTreeRepository.save(new MentorTree(5L, 4L, 2L));
    */}

}
