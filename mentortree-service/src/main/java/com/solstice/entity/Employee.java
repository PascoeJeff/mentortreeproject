package com.solstice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private Long employeeNumber;
    @JsonIgnore
    private String office;
    @JsonIgnore
    private String title;
    @JsonIgnore
    private String email;
    private String imageUrl;
    private Link link;


    public Employee() {
    }

    public Employee(LinkedHashMap<String, Object> o) {
        this.id = Long.parseLong(String.valueOf(o.get("id")));
        this.firstName = (String)o.get("firstName");
        this.lastName = (String)o.get("lastName");
        this.imageUrl = (String)o.get("imageUrl");
    }

    public Employee(Long id, String firstName, String lastName, Long employeeNumber, String office, String title, String email, String imageUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeNumber = employeeNumber;
        this.office = office;
        this.title = title;
        this.email = email;
        this.imageUrl = imageUrl;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

}
