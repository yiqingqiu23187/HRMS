package edu.fudan.hrms.controller;

import edu.fudan.hrms.controller.req.ApplySalaryReq;
import edu.fudan.hrms.controller.req.HandlerSalaryReq;
import edu.fudan.hrms.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@CrossOrigin
public class SalaryController {
    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("/salary/get")
    public ResponseEntity<?> getSalary(@RequestParam("userID") int userID) {
        return ResponseEntity.ok(salaryService.getSalary((long) userID));
    }

    @PostMapping("/salary/apply")
    public ResponseEntity<?> applySalary(@RequestBody ApplySalaryReq req) {
        salaryService.saveSalaryApplication(req);
        return ResponseEntity.ok(new HashMap<String, Object>() {
            {
                put("success", true);
            }
        });
    }

    @GetMapping("/salary/allApplications")
    public ResponseEntity<?> appSalaryApplications(@RequestParam("userID") int userID) {
        return ResponseEntity.ok(salaryService.getAllSalaryApplications(userID));
    }

    @PostMapping("/salary/handler")
    public ResponseEntity<?> handlerSalaryApplication(@RequestBody HandlerSalaryReq req) {
        salaryService.handlerSalaryApplication(req);
        return ResponseEntity.ok(new HashMap<String, Object>() {
            {
                put("success", true);
            }
        });
    }

    @GetMapping("salary")
    public ResponseEntity<?> getApplication(@RequestParam("applicationID") int applicationID) {
        return ResponseEntity.ok(salaryService.getApplication((long) applicationID));
    }
}
