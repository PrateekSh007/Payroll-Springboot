package com.Payroll.PayrollAuth.controller;


import com.Payroll.PayrollAuth.service.PaySlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/payslip")
public class PaySlipController {

    @Autowired
    private PaySlipService paySlipService ;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{employeeId}")
    public ResponseEntity<byte[]> generatePaySlip(@PathVariable Long employeeId,
                                                  @RequestParam String month) {
        LocalDate date = LocalDate.parse(month + "-01");

        byte[] pdfBytes = paySlipService.generatePaySlip(employeeId, date);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=payslip-" + employeeId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
