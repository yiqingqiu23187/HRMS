package edu.fudan.hrms.repository;

import edu.fudan.hrms.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department getDepartmentByDepartmentID(Long departmentID);

    Department getDepartmentByDepartmentName(String departmentName);

    List<Department> findAll();
}
