package com.Payroll.PayrollAuth.controller;

import com.Payroll.PayrollAuth.entity.Leave;
import com.Payroll.PayrollAuth.entity.LeaveStatus;
import com.Payroll.PayrollAuth.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService ;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{employeeId}")
    public ResponseEntity<Leave> applyLeave(@PathVariable Long employeeId,
                                            @RequestBody Leave leave) {
        return ResponseEntity.ok(leaveService.applyLeave(employeeId, leave));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{leaveId}/status")
    public ResponseEntity<Leave> updateLeaveStatus(@PathVariable Long leaveId,
                                                   @RequestParam LeaveStatus status) {
        return ResponseEntity.ok(leaveService.updateLeaveStatus(leaveId, status));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Leave>> getLeavesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveService.getLeavesByEmployee(employeeId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Leave>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }
}
