package com.solstice;

import com.solstice.dao.EmployeeRepository;
import com.solstice.entity.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class EmployeeServiceApplication {

    public static void main(String[] args) {
//        SpringApplication.run(EmployeeServiceApplication.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(EmployeeServiceApplication.class, args);
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
        employeeRepository.save(new Employee("Joe","",1L,"","","",""));
        employeeRepository.save(new Employee("Mike","",2L,"","","",""));
        employeeRepository.save(new Employee("Jeff","",3L,"","","",""));
        employeeRepository.save(new Employee("Sher","",4L,"","","",""));
        employeeRepository.save(new Employee("John","",5L,"","","",""));
        employeeRepository.save(new Employee("DeleteMe","",6L,"","","",""));
    }



}
