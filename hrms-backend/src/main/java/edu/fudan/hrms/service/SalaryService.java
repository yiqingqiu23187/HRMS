package edu.fudan.hrms.service;

import edu.fudan.hrms.common.Constants;
import edu.fudan.hrms.controller.req.ApplySalaryReq;
import edu.fudan.hrms.controller.req.HandlerSalaryReq;
import edu.fudan.hrms.controller.resp.GetSalaryInfoResp;
import edu.fudan.hrms.entity.Application;
import edu.fudan.hrms.entity.Department;
import edu.fudan.hrms.entity.User;
import edu.fudan.hrms.repository.ApplicationRepository;
import edu.fudan.hrms.repository.DepartmentRepository;
import edu.fudan.hrms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final ApplicationRepository applicationRepository;

    @Autowired
    public SalaryService(UserRepository userRepository, DepartmentRepository departmentRepository, ApplicationRepository applicationRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.applicationRepository = applicationRepository;
    }

    public GetSalaryInfoResp getSalary(Long userID) {
        User user = userRepository.getUserByUserID(userID);
        Department department = departmentRepository.getDepartmentByDepartmentID(user.getDepartmentID());
        return new GetSalaryInfoResp(userID, user.name, department.getDepartmentID(), department.getDepartmentName(),
                department.getDepartmentSalary(), user.getPersonalSalary(), department.getDepartmentSalary() + user.getPersonalSalary());
    }

    public void saveSalaryApplication(ApplySalaryReq req) {
        User user = userRepository.getUserByUserID((long) req.getUserID());
        if (req.getType() == Constants.TYPE_SALARY) {
            applicationRepository.save(new Application((long) req.getUserID(), req.getType(), Constants.STATUS_PROCESSING, (long) user.getPersonalSalary(), req.getNewValue(), req.getTime()));
        } else {
            applicationRepository.save(new Application((long) req.getUserID(), req.getType(), Constants.STATUS_PROCESSING, user.getDepartmentID(), req.getNewValue(), req.getTime()));
        }
    }

    public List<Application> getAllSalaryApplications(int userID) {
        User user = userRepository.getUserByUserID((long) userID);
        if (user.userAuth == Constants.HR) { // HR
            List<Application> result = new ArrayList<>();
            applicationRepository.findAll().forEach(result::add);
            return result;
        } else { // staff
            return applicationRepository.getApplicationsByUserID(user.userID);
        }
    }

    public void handlerSalaryApplication(HandlerSalaryReq req) {
        Application application = applicationRepository.getApplicationByApplicationID((long) req.getApplicationID());
        if (req.isAgree()) {
            application.setStatus(Constants.STATUS_AGREED);
            User user = userRepository.getUserByUserID(application.userID);
            if (application.type == Constants.TYPE_SALARY) {
                user.setPersonalSalary(application.newValue.intValue());
            } else {
                user.setDepartmentID(application.newValue);
            }
            userRepository.save(user);
        } else {
            application.setStatus(Constants.STATUS_REJECTED);
        }
        application.setReason(req.getReason());
        applicationRepository.save(application);
    }

    public Application getApplication(Long applicationID) {
        return applicationRepository.getApplicationByApplicationID(applicationID);
    }
}
