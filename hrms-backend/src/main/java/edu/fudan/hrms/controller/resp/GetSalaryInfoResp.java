package edu.fudan.hrms.controller.resp;

public class GetSalaryInfoResp {
    Long userID;
    String name;
    Long departmentID;
    String departmentName;
    int departmentSalary;
    int personalSalary;
    int totalSalary;

    public GetSalaryInfoResp(Long userID, String name, Long departmentID, String departmentName, int departmentSalary, int personalSalary, int totalSalary) {
        this.userID = userID;
        this.name = name;
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.departmentSalary = departmentSalary;
        this.personalSalary = personalSalary;
        this.totalSalary = totalSalary;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Long departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartmentSalary() {
        return departmentSalary;
    }

    public void setDepartmentSalary(int departmentSalary) {
        this.departmentSalary = departmentSalary;
    }

    public int getPersonalSalary() {
        return personalSalary;
    }

    public void setPersonalSalary(int personalSalary) {
        this.personalSalary = personalSalary;
    }

    public int getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(int totalSalary) {
        this.totalSalary = totalSalary;
    }
}
