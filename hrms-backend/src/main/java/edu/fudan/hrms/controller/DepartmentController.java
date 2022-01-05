package edu.fudan.hrms.controller;

import edu.fudan.hrms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department/nameList")
    public ResponseEntity<?> getNameInfo() {
        return ResponseEntity.ok(departmentService.getNameInfo());
    }

    @GetMapping("/department/list")
    public ResponseEntity<?> getFullInfo() {
        return ResponseEntity.ok(departmentService.getFullInfo());
    }
}
