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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long userID;
    public String password;
    public int userAuth; // Constants.UserAuth
    public String name;
    public Long departmentID;
    public int personalSalary;

    public User(String password, int userAuth, String name, Long departmentID, int personalSalary) {
        this.password = password;
        this.userAuth = userAuth;
        this.name = name;
        this.departmentID = departmentID;
        this.personalSalary = personalSalary;
    }
}
