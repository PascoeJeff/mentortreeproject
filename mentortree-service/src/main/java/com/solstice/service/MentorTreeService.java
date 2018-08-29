package com.solstice.service;

import com.solstice.dao.MentorTreeRepository;
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

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}


