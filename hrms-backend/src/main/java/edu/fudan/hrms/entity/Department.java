package edu.fudan.hrms.entity;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long departmentID;
    public String departmentName;
    public int departmentSalary;

    public Department(String departmentName, int departmentSalary) {
        this.departmentName = departmentName;
        this.departmentSalary = departmentSalary;
    }
}
