package com.solstice.service;

import com.solstice.dao.MentorTreeRepository;
import com.solstice.entity.Employee;
import com.solstice.entity.MentorTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class MentorTreeService {

    @Autowired
    MentorTreeRepository mentorTreeRepository;

    private RestTemplate restTemplate;

    public MentorTreeService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Long> getEmployeeIdsFromMentorId(Long id) {
        List<MentorTree> mentorTrees = mentorTreeRepository.findAllByMentorId(id);
        List<Long> ids = mentorTrees.stream().map(MentorTree::getEmployeeId).collect(toList());
        return ids;
    }

    public Employee getEmployeeFromEmployeeService(String uri, Long id) {
        return this.restTemplate.getForObject(uri, Employee.class, id);
    }

    public List<Employee> getEmployeeFromEmployeeService(String uri, List<Long> ids) {

        String queryString = "?ids=";
        for(Long l : ids) {
            queryString.concat(String.valueOf(l));
        }

        return (List<Employee>)this.restTemplate.getForObject(uri+queryString, List.class);

    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}


