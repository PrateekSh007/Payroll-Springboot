package com.Payroll.PayrollAuth.service;

import com.Payroll.PayrollAuth.entity.User;
import com.Payroll.PayrollAuth.model.UserResponse;
import com.Payroll.PayrollAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository ;

    public UserResponse registerUser(User user) {
        if(userRepository.existsByUserName(user.getUserName())) {
            throw new IllegalArgumentException("Username already taken");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("Email already in use");
        }

        User savedUser  = userRepository.save(user) ;

        return UserResponse.builder()
                .userName(savedUser.getUserName())
                .role(savedUser.getRole()).build() ;
    }

    public UserResponse loginUser(User loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUserName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return UserResponse.builder()
                .userName(user.getUserName())
                .role(user.getRole())
                .build();
    }

}
