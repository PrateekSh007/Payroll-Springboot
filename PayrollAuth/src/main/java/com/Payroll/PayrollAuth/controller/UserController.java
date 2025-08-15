package com.Payroll.PayrollAuth.controller;


import com.Payroll.PayrollAuth.entity.User;
import com.Payroll.PayrollAuth.model.UserResponse;
import com.Payroll.PayrollAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService ;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody User user){
        return ResponseEntity.ok(userService.registerUser(user)) ;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody User loginRequest) {
        return ResponseEntity.ok(userService.loginUser(loginRequest));
    }

}
