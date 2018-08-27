package com.solstice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MentorTree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private Long employeeId;
    private Long mentorId;
    private Long treeLeadId;

    public MentorTree() {
    }

    public MentorTree(Long employeeId, Long mentorId, Long treeLeadId) {
        this.employeeId = employeeId;
        this.mentorId = mentorId;
        this.treeLeadId = treeLeadId;
    }


    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getMentorId() {
        return mentorId;
    }

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }

    public Long getTreeLeadId() {
        return treeLeadId;
    }

    public void setTreeLeadId(Long treeLeadId) {
        this.treeLeadId = treeLeadId;
    }
}
