package com.Payroll.PayrollAuth.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String userName ;
    private String password ;
    private String email ;
}
