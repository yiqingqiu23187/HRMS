package edu.fudan.hrms;

import edu.fudan.hrms.entity.Department;
import edu.fudan.hrms.entity.Employee;
import edu.fudan.hrms.entity.User;
import edu.fudan.hrms.repository.DepartmentRepository;
import edu.fudan.hrms.repository.EmployeeRepository;
import edu.fudan.hrms.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HrmsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrmsBackendApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(DepartmentRepository departmentRepository, UserRepository userRepository, EmployeeRepository employeeRepository) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                DepartmentLoader(departmentRepository);
                UserLoader(userRepository);
                EmployeeLoader(employeeRepository);
            }

            private void DepartmentLoader(DepartmentRepository departmentRepository) {
                Department department1 = new Department(1L, "人力部", 2000);
                departmentRepository.save(department1);
                Department department2 = new Department(2L, "销售部", 4000);
                departmentRepository.save(department2);
            }

            private void UserLoader(UserRepository userRepository) {
                User user1 = new User("password", 1, "admin", 1L, 1223);
                userRepository.save(user1);
                User user2 = new User("password", 0, "user1", 2L, 4333);
                userRepository.save(user2);
            }

            private void EmployeeLoader(EmployeeRepository employeeRepository) {
                Employee employee1 = new Employee("admin", "男", "11111111111", "address1", "人力部", 1223, 3L);
                employeeRepository.save(employee1);
                Employee employee2 = new Employee("user1", "男", "22222222222", "address2", "销售部", 4333, 4L);
                employeeRepository.save(employee2);
            }
        };
    }
}
