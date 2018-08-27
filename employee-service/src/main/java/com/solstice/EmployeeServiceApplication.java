package com.solstice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EmployeeServiceApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(EmployeeServiceApplication.class, args);
//
//        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
//        employeeRepository.save(new Employee("joe","",(long)1,"","","",""));

    }



}
