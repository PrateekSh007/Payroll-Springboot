package com.Payroll.PayrollAuth.repository;

import com.Payroll.PayrollAuth.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {
    List<Salary> findByEmployeeId(Long employeeId);
}
