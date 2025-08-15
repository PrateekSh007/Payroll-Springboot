package com.Payroll.PayrollAuth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String employeeId;
    private String firstName ;
    private String lastName ;
    private String mobileNumber;
    @Enumerated(EnumType.STRING)
    private Designation designation;
    private Role role= Role.USER ;
    private String email ;
}
