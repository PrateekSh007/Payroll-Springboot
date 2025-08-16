package com.Payroll.PayrollAuth.service;

import com.Payroll.PayrollAuth.entity.Employee;
import com.Payroll.PayrollAuth.entity.Salary;
import com.Payroll.PayrollAuth.repository.EmployeeRepository;
import com.Payroll.PayrollAuth.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository ;

    @Autowired
    private EmployeeRepository employeeRepository ;

    public Salary addSalary(Long employeeId,Salary salary) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        double net = salary.getBaseSalary()
                + (salary.getAllowances() != null ? salary.getAllowances() : 0.0)
                - (salary.getDeductions() != null ? salary.getDeductions() : 0.0);

        salary.setNetSalary(net);
        salary.setEmployee(employee);

        return salaryRepository.save(salary);
    }

    public Salary updateSalary(Long id, Salary updated) {
        Salary existing = salaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salary record not found"));

        existing.setBaseSalary(updated.getBaseSalary());
        existing.setAllowances(updated.getAllowances());
        existing.setDeductions(updated.getDeductions());

        double net = existing.getBaseSalary()
                + (existing.getAllowances() != null ? existing.getAllowances() : 0.0)
                - (existing.getDeductions() != null ? existing.getDeductions() : 0.0);
        existing.setNetSalary(net);

        return salaryRepository.save(existing);
    }

    public List<Salary> getSalaryByEmployee(Long employeeId) {
        return salaryRepository.findByEmployeeId(employeeId);
    }

    public void deleteSalary(Long id) {
        salaryRepository.deleteById(id);
    }

    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }
}
