package com.solstice.domain;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class EmployeeInfo {

    private Long id;
    private String firstName;
    private String lastName;
    private Long employeeNumber;
    private String office;
    private String title;
    private String email;
    private String imageUrl;


    public EmployeeInfo() {
    }

    public EmployeeInfo(LinkedHashMap<String, Object> o) {
        this.id = Long.parseLong(String.valueOf(o.get("id")));
        this.firstName = (String)o.get("firstName");
        this.lastName = (String)o.get("lastName");
        this.imageUrl = (String)o.get("imageUrl");
    }

    public EmployeeInfo(Long id, String firstName, String lastName, Long employeeNumber, String office, String title, String email, String imageUrl) {
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

}
