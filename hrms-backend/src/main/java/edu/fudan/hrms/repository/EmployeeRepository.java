package edu.fudan.hrms.repository;

import edu.fudan.hrms.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAll();

    List<Employee> getEmployeesByName(String name);

    Employee getEmployeeByUserID(Long userID);
}
