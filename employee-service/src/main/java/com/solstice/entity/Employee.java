package com.solstice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Long employeeNumber;
    private String office;
    private String title;
    private String email;
    private String imageUrl;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Long employeeNumber, String office, String title, String email, String imageUrl) {
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

    public String getFirstName() {
        return firstName;
    }
    @JsonProperty(required = true)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    @JsonProperty(required = true)
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getEmployeeNumber() {
        return employeeNumber;
    }
    @JsonProperty(required = true)
    public void setEmployeeNumber(Long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getOffice() {
        return office;
    }
    @JsonProperty(required = true)
    public void setOffice(String office) {
        this.office = office;
    }

    public String getTitle() {
        return title;
    }
    @JsonProperty(required = true)
    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }
    @JsonProperty(required = true)
    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    @JsonProperty(required = true)
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
