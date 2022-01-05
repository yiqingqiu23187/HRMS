package edu.fudan.hrms.controller;

import edu.fudan.hrms.entity.User;
import edu.fudan.hrms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam("userID") int userID, @RequestParam("password") String password) {
        User user = userService.getUser(userID, password);
        if (user == null) {
            return ResponseEntity.ok(ResponseEntity.badRequest());
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("userID", user.getUserID());
        result.put("name", user.getName());
        result.put("userAuth", user.getUserAuth());
        return ResponseEntity.ok(result);
    }
}
