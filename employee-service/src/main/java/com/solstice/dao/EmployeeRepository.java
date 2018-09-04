package com.solstice.dao;

import com.solstice.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE id IN :ids")
    List<Employee> findEmployeesByIdList(@Param("ids") List<Long> ids);

    List<Employee> findEmployeeByIdIn(List<Long> ids);

}
