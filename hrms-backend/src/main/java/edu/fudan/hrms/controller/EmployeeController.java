package edu.fudan.hrms.controller;

import edu.fudan.hrms.controller.req.AddEmployeeReq;
import edu.fudan.hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@CrossOrigin
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/allInformation")
    public ResponseEntity<?> getInfo(@RequestParam("search") String search) {
        if (search.equals("")) {
            return ResponseEntity.ok(employeeService.getAllInfo());
        } else {
            return ResponseEntity.ok(employeeService.getSearchInfo(search));
        }
    }

    @PostMapping("/employee/addEmployee")
    public ResponseEntity<?> addEmployee(@RequestBody AddEmployeeReq req) {
        employeeService.addEmployee(req);
        return ResponseEntity.ok(new HashMap<String, Object>() {
            {
                put("success", true);
            }
        });
    }

    @GetMapping("/employee/personalInformation")
    public ResponseEntity<?> personalInfo(@RequestParam("userID") int userID) {
        return ResponseEntity.ok(employeeService.personalInfo(userID));
    }
}
