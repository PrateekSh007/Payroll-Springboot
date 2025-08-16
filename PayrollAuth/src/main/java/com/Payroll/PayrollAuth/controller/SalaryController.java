package com.Payroll.PayrollAuth.controller;

import com.Payroll.PayrollAuth.entity.Salary;
import com.Payroll.PayrollAuth.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService ;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{employeeId}")
    public ResponseEntity<Salary> addSalary(@PathVariable Long employeeId,
                                            @RequestBody Salary salary) {
        return ResponseEntity.ok(salaryService.addSalary(employeeId, salary));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long id,
                                               @RequestBody Salary salary) {
        return ResponseEntity.ok(salaryService.updateSalary(id, salary));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Salary>> getSalaryByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(salaryService.getSalaryByEmployee(employeeId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Salary>> getAllSalaries() {
        return ResponseEntity.ok(salaryService.getAllSalaries());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        salaryService.deleteSalary(id);
        return ResponseEntity.noContent().build();
    }
}
