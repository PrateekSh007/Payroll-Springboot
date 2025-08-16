package com.Payroll.PayrollAuth.service;


import com.Payroll.PayrollAuth.entity.Employee;
import com.Payroll.PayrollAuth.entity.Leave;
import com.Payroll.PayrollAuth.entity.LeaveStatus;
import com.Payroll.PayrollAuth.repository.EmployeeRepository;
import com.Payroll.PayrollAuth.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository ;

    @Autowired
    private EmployeeRepository employeeRepository ;

    public Leave applyLeave(Long employeeId, Leave leave) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        leave.setEmployee(employee);
        leave.setStatus(LeaveStatus.PENDING);
        return leaveRepository.save(leave);
    }

    public Leave updateLeaveStatus(Long leaveId, LeaveStatus status) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus(status);
        return leaveRepository.save(leave);
    }

    public List<Leave> getLeavesByEmployee(Long employeeId) {
        return leaveRepository.findByEmployeeId(employeeId);
    }

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }
}
