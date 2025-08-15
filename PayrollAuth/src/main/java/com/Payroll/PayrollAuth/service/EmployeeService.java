package com.Payroll.PayrollAuth.service;

import com.Payroll.PayrollAuth.entity.Employee;
import com.Payroll.PayrollAuth.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository ;

    public Employee addEmployee(Employee employee) {
        if(employeeRepository.existsByMobileNumber(employee.getMobileNumber())){
            throw new RuntimeException("Mobile number already exists!");
        }
        employee.setEmployeeId("EMP-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
        return employeeRepository.save(employee);
    }

    public Optional<Employee> viewEmployeebyId(Long id) {
        return employeeRepository.findById(id) ;
    }

    public Employee updateEmployee(Long id,Employee updatedEmployee){

    }
}
