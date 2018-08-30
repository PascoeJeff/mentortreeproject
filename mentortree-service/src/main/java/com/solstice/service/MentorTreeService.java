package com.solstice.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.solstice.controller.MentorTreeRestController;
import com.solstice.dao.MentorTreeRepository;
import com.solstice.entity.Employee;
import com.solstice.entity.MentorTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RefreshScope
@Service
public class MentorTreeService {

    @Autowired
    MentorTreeRepository mentorTreeRepository;

    @Autowired
    private EurekaClient discoveryClient;

    private RestTemplate restTemplate;

    public MentorTreeService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Long> getEmployeeIdsFromMentorId(Long id) {
        List<MentorTree> mentorTrees = mentorTreeRepository.findAllByMentorId(id);
        List<Long> ids = mentorTrees.stream().map(MentorTree::getEmployeeId).collect(toList());
        return ids;
    }

    public List<Long> getEmployeeIdsFromTreeLeadId(Long id) {
        List<MentorTree> mentorTrees = mentorTreeRepository.findAllByTreeLeadId(id);
        List<Long> ids = mentorTrees.stream().map(MentorTree::getEmployeeId).collect(toList());
        List<Long> mentorIds = mentorTrees.stream().map(MentorTree::getMentorId).collect(toList());
        ids.addAll(mentorIds);

        //to do remove duplicates
        return ids;
    }

    public Object getEmployeeFromEmployeeService(String uri, Long id) {
        return this.restTemplate.getForObject(uri, Object.class, id);
    }

    public List<Object> getEmployeesFromEmployeeService(String uri, List<Long> ids) {
        StringBuilder sb = new StringBuilder();
        for (Long i : ids) {
            sb.append(i);
            sb.append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return this.restTemplate.getForObject(uri, List.class, sb);
    }

    public boolean updateMentorIdForEmployee(Long id, Long mentorId) {
        MentorTree mentorTree = mentorTreeRepository.findByEmployeeId(id);
        mentorTree.setMentorId(mentorId);
        if (mentorTreeRepository.save(mentorTree) != null) {
            return true;
        }
        return false;
    }

    public boolean updateTreeLeadIdForEmployee(Long id, Long treeLeadId) {
        MentorTree mentorTree = mentorTreeRepository.findByEmployeeId(id);
        mentorTree.setTreeLeadId(treeLeadId);
        if (mentorTreeRepository.save(mentorTree) != null) {
            return true;
        }
        return false;
    }

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

    public Resources<Employee> addLinkToEmployee(List<Employee> employeeList) {
        Resources<Employee> resources = new Resources<Employee>(employeeList);
        for (final Employee resource : resources) {
            Link selfLink = linkTo(methodOn(MentorTreeRestController.class)
                    .getEmployeeById(resource.getId())).withSelfRel();
            resource.setLink(selfLink);
        }
        return resources;
    }

    public List<Employee> getEmployeesFromHashMap(List<Object> menteeList) {
        List<Employee> employeeList = new ArrayList<>();

        for (Object o : menteeList) {
            employeeList.add(new Employee((LinkedHashMap<String, Object>) o));
        }
        return employeeList;
    }


    public String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("zuul-server", false);
        return instance.getHomePageUrl();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



}


