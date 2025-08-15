package com.Payroll.PayrollAuth.repository;

import com.Payroll.PayrollAuth.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    boolean existsByMobileNumber(String mobileNumber);
}
