package com.Payroll.PayrollAuth.service;

import com.Payroll.PayrollAuth.entity.Employee;
import com.Payroll.PayrollAuth.entity.LeaveStatus;
import com.Payroll.PayrollAuth.entity.Salary;
import com.Payroll.PayrollAuth.repository.EmployeeRepository;
import com.Payroll.PayrollAuth.repository.LeaveRepository;
import com.Payroll.PayrollAuth.repository.SalaryRepository;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.io.ByteArrayOutputStream;

@Service
public class PaySlipService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    public byte[] generatePaySlip(Long employeeId, LocalDate month) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Salary salary = salaryRepository.findByEmployeeId(employeeId).stream()
                .filter(s -> s.getMonth().getYear() == month.getYear() &&
                        s.getMonth().getMonthValue() == month.getMonthValue())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Salary record not found for this month"));

        long leaveCount = leaveRepository.findByEmployeeId(employeeId).stream()
                .filter(l -> l.getStatus() == LeaveStatus.APPROVED &&
                        l.getStartDate().getMonthValue() == month.getMonthValue())
                .count();

        double perDayDeduction = salary.getBaseSalary() / 30;
        double leaveDeduction = leaveCount * perDayDeduction;

        double finalNetSalary = salary.getNetSalary() - leaveDeduction;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            Paragraph title = new Paragraph("Pay Slip - " + month.getMonth() + " " + month.getYear())
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(16);
            document.add(title);

            document.add(new Paragraph("Employee ID: " + employee.getEmployeeId()));
            document.add(new Paragraph("Name: " + employee.getFirstName() + " " + employee.getLastName()));
            document.add(new Paragraph("Designation: " + employee.getDesignation()));
            document.add(new Paragraph("Email: " + employee.getEmail()));
            document.add(new Paragraph(" "));

            Table table = new Table(2);
            table.addCell("Base Salary");
            table.addCell(String.valueOf(salary.getBaseSalary()));

            table.addCell("Allowances");
            table.addCell(String.valueOf(salary.getAllowances()));

            table.addCell("Deductions");
            table.addCell(String.valueOf(salary.getDeductions()));

            table.addCell("Approved Leaves");
            table.addCell(String.valueOf(leaveCount));

            table.addCell("Leave Deduction");
            table.addCell(String.valueOf(leaveDeduction));

            table.addCell("Net Salary (Before Leaves)");
            table.addCell(String.valueOf(salary.getNetSalary()));

            table.addCell("Final Net Salary");
            table.addCell(String.valueOf(finalNetSalary));

            document.add(table);

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating pay slip", e);
        }
    }
}
