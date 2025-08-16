package com.Payroll.PayrollAuth.service;

import com.Payroll.PayrollAuth.entity.User;
import com.Payroll.PayrollAuth.model.UserResponse;
import com.Payroll.PayrollAuth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    public UserResponse registerUser(User user) {
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new IllegalArgumentException("Username already taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser  = userRepository.save(user);

        // (Optional) issue token on register too
        UserDetails ud = new CustomUserDetails(savedUser);
        String token = jwtService.generateToken(ud, Map.of("role", savedUser.getRole().name()));

        return UserResponse.builder()
                .userName(savedUser.getUserName())
                .role(savedUser.getRole())
                .token(token)
                .build();
    }

    public UserResponse loginUser(User loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUserName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        UserDetails ud = userDetailsService.loadUserByUsername(user.getUserName());
        String token = jwtService.generateToken(ud, Map.of("role", "ROLE_" + user.getRole().name()));

        return UserResponse.builder()
                .userName(user.getUserName())
                .role(user.getRole())
                .token(token)
                .build();
    }
}
