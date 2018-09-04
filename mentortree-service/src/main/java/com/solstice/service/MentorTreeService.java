package com.solstice.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.solstice.controller.MentorTreeRestController;
import com.solstice.dao.MentorTreeRepository;
import com.solstice.domain.Employee;
import com.solstice.domain.EmployeeInfo;
import com.solstice.entity.MentorTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RefreshScope
@Service
public class MentorTreeService {


    @Autowired
    private EurekaClient discoveryClient;
    MentorTreeRepository mentorTreeRepository;
    RestTemplate restTemplate;

    public MentorTreeService(RestTemplateBuilder restTemplateBuilder, MentorTreeRepository mentorTreeRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.mentorTreeRepository = mentorTreeRepository;
    }

    //Retrieves a list of the employee ids belonging to the mentees of a given mentor.
    public List<Long> getEmployeeIdsFromMentorId(Long id) {
        List<MentorTree> mentorTrees = mentorTreeRepository.findAllByMentorId(id);
        List<Long> ids = mentorTrees.stream().map(MentorTree::getEmployeeId).collect(toList());
        return ids;
    }

    //Retrieves a list of the employee ids belonging to the any employee that exists in the given tree leads tree.
    public List<Long> getEmployeeIdsFromTreeLeadId(Long id) {
        List<MentorTree> mentorTrees = mentorTreeRepository.findAllByTreeLeadId(id);
        List<Long> ids = mentorTrees.stream().map(MentorTree::getEmployeeId).collect(toList());
        List<Long> mentorIds = mentorTrees.stream().map(MentorTree::getMentorId).collect(toList());
        ids.addAll(mentorIds);
        return ids;
    }

    //Calls out to the employee-service and retrieves a single employee based on the given employee id.
    public EmployeeInfo getEmployeeFromEmployeeService(String uri, Long id) {
        return this.restTemplate.getForObject(uri, EmployeeInfo.class, id);
    }

    //Calls out to the employee-service and retrieves a list of Employees, one for each id in the provided id list.
    public List<Employee> getEmployeesFromEmployeeService(String uri, List<Long> ids) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("ids", formatUriParameters(ids));

        ResponseEntity<List<Employee>> rateResponse =
                restTemplate.exchange(builder.toUriString(),
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        new ParameterizedTypeReference<List<Employee>>() {
                        });

        return rateResponse.getBody();
    }

    //This is used to update the mentor for an employee.
    public boolean updateMentorIdForEmployee(Long id, Long mentorId) {
        MentorTree mentorTree = mentorTreeRepository.findByEmployeeId(id);
        mentorTree.setMentorId(mentorId);
        if (mentorTreeRepository.save(mentorTree) != null) {
            return true;
        }
        return false;
    }

    //This is used to update the tree lead for an employee.
    public boolean updateTreeLeadIdForEmployee(Long id, Long treeLeadId) {
        MentorTree mentorTree = mentorTreeRepository.findByEmployeeId(id);
        mentorTree.setTreeLeadId(treeLeadId);
        if (mentorTreeRepository.save(mentorTree) != null) {
            return true;
        }
        return false;
    }

    //This is used to remove a given employee from any mentorTree structures that it is found in.
    public void deleteEmployee(Long id) {
        List<MentorTree> mentorTreesWithIdAsEmployee = mentorTreeRepository.findAllByEmployeeId(id)
                .parallelStream()
                .map(mentorTree -> {
                    mentorTree.setEmployeeId(null);
                    return mentorTree;
                }).collect(toList());

        List<MentorTree> mentorTreesWithIdAsMentor = mentorTreeRepository.findAllByMentorId(id)
                .parallelStream()
                .map(mentorTree -> {
                    mentorTree.setMentorId(null);
                    return mentorTree;
                }).collect(toList());

        List<MentorTree> mentorTreesWithIdAsTreeLead = mentorTreeRepository.findAllByTreeLeadId(id)
                .parallelStream()
                .map(mentorTree -> {
                    mentorTree.setTreeLeadId(null);
                    return mentorTree;
                }).collect(toList());

        mentorTreeRepository.saveAll(mentorTreesWithIdAsEmployee);
        mentorTreeRepository.saveAll(mentorTreesWithIdAsMentor);
        mentorTreeRepository.saveAll(mentorTreesWithIdAsTreeLead);
    }

    //Used to add a link to an employee that can be used to access the resource.
    public Resources<Employee> addLinkToEmployee(List<Employee> employeeList) {
        Resources<Employee> resources = new Resources<>(employeeList);
        for (final Employee resource : resources) {
            Link selfLink = linkTo(methodOn(MentorTreeRestController.class)
                    .getEmployeeById(resource.getId())).withSelfRel();
            resource.setLink(selfLink);
        }
        return resources;
    }

    //A simpler helper function.
    public String getSuccessMessage(boolean updateSuccess, String s) {
        if (updateSuccess == true) {
            return s;
        } else {
            return "Unsuccessful";
        }
    }

    //Used to get the a service url for the provided service
    public String serviceUrl(String serviceName) {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka(serviceName, false);
        return instance.getHomePageUrl();
    }

    //Used to take a list of Long and format it as a comma delimitted string.
    public String formatUriParameters(List<Long> ids) {
        StringBuilder sb = new StringBuilder();
        for (Long i : ids) {
            sb.append(i);
            sb.append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}


