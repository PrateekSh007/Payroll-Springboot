package com.Payroll.PayrollAuth.model;

import com.Payroll.PayrollAuth.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponse {
    private String userName;
    private Role role ;
}
