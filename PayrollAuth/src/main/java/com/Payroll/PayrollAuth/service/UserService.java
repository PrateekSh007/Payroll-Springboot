package com.Payroll.PayrollAuth.service;

import com.Payroll.PayrollAuth.dto.RegisterResponse;
import com.Payroll.PayrollAuth.model.Role;
import com.Payroll.PayrollAuth.model.User;
import com.Payroll.PayrollAuth.repository.UserRepository;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public User register(RegisterResponse registerResponse){
        if(userRepository.existByUsername(registerResponse.getUsername())){
            throw new IllegalArgumentException("Username already exists") ;
        }
        if(userRepository.existByEmail(registerResponse.getEmail())) {
            throw new IllegalArgumentException("email already exists") ;
        }
        User user = User.builder()
                .username(registerResponse.getUsername())
                .password(passwordEncoder.encode(registerResponse.getPassword()))
                .email(registerResponse.getEmail())
                .role(Collections.singleton(Role.ROLE_USER))
                .build() ;

        return userRepository.save(user);


    }
}
