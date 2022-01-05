package edu.fudan.hrms.service;

import edu.fudan.hrms.entity.Department;
import edu.fudan.hrms.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<String> getNameInfo() {
        List<Department> departments = departmentRepository.findAll();
        List<String> departmentNames = new ArrayList<>();
        for (Department department : departments) {
            departmentNames.add(department.getDepartmentName());
        }
        return departmentNames;
    }

    public List<Department> getFullInfo() {
        return departmentRepository.findAll();
    }
}
