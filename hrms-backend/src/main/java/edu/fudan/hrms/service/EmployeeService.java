package edu.fudan.hrms.service;

import edu.fudan.hrms.common.Constants;
import edu.fudan.hrms.controller.req.AddEmployeeReq;
import edu.fudan.hrms.entity.Department;
import edu.fudan.hrms.entity.Employee;
import edu.fudan.hrms.entity.User;
import edu.fudan.hrms.repository.DepartmentRepository;
import edu.fudan.hrms.repository.EmployeeRepository;
import edu.fudan.hrms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    @Autowired
    public EmployeeService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    public List<Employee> getAllInfo() {
        return employeeRepository.findAll();
    }

    public List<Employee> getSearchInfo(String name) {
        return employeeRepository.getEmployeesByName(name);
    }

    public synchronized void addEmployee(AddEmployeeReq req) {
        Department department = departmentRepository.getDepartmentByDepartmentName(req.getDepartmentName());
        User newUser = new User(req.getName() + req.getPhoneNumber(), Constants.STAFF, req.getName(), department.getDepartmentID(), req.getTotalSalary());
        userRepository.save(newUser);
        User user = userRepository.getUserByPasswordAndName(req.getName() + req.getPhoneNumber(), req.getName());
        employeeRepository.save(new Employee(req.getName(), req.getSex(), req.getPhoneNumber(), req.getAddress(), req.getDepartmentName(), req.getTotalSalary(), user.userID));
    }

    public List<Employee> personalInfo(int userID) {
        List<Employee> employee = new ArrayList<>();
        employee.add(employeeRepository.getEmployeeByUserID((long) userID));
        return employee;
    }
}
