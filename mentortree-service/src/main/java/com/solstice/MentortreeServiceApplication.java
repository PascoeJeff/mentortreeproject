package com.solstice;

import com.solstice.dao.MentorTreeRepository;
import com.solstice.entity.MentorTree;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class MentortreeServiceApplication {

    public static void main(String[] args) {
//        SpringApplication.run(MentortreeServiceApplication.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(MentortreeServiceApplication.class, args);
        MentorTreeRepository mentorTreeRepository = context.getBean(MentorTreeRepository.class);
        mentorTreeRepository.save(new MentorTree(1L, 2L, 2L));
        mentorTreeRepository.save(new MentorTree(2L, null, null));
        mentorTreeRepository.save(new MentorTree(3L, 1L, 2L));
        mentorTreeRepository.save(new MentorTree(4L, 1L, 2L));
        mentorTreeRepository.save(new MentorTree(5L, 4L, 2L));
    }

}
