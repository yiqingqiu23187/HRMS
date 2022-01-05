package edu.fudan.hrms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long employeeID;
    public String name;
    public String sex;
    public String phoneNumber;
    public String address;
    public String departmentName;
    public int totalSalary;
    public Long userID;

    public Employee(String name, String sex, String phoneNumber, String address, String departmentName, int totalSalary, Long userID) {
        this.name = name;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.departmentName = departmentName;
        this.totalSalary = totalSalary;
        this.userID = userID;
    }
}
