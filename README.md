# Payroll Management System

  A Spring Boot based Payroll Management System with authentication, role-based access, employee management, salary, leave management, and payslip generation.
  This project is built to demonstrate a basic-to-moderate microservice-style architecture with JWT authentication.

# Features

 1. Authentication & Authorization
  Secure registration and login using JSON Web Tokens (JWT).
  
 2. Role-Based Access Control
  Pre-defined roles (Admin, User, Accountant) with specific permissions.
  
 3. Employee Management
  Admins can perform full CRUD (Create, Read, Update, Delete) operations on employees.
  
 4.  Salary Management
  Admins can manage salary structures, including base pay, allowances, and deductions.
  
 5. Leave Management
  Employees can apply for leave, which managers or admins can approve/reject.
  
 6. Payslip Generation
  Automatically generate monthly payslips in PDF format, detailing salary, leaves, and deductions.

# Tech Stack

 1. Backend: Spring Boot 3, Spring Security 6
  
 2. Authentication: JSON Web Tokens (JWT)
  
 3. Database: MySQL (or PostgreSQL)
  
 4. ORM: Hibernate / Spring Data JPA
  
 5. Build Tool: Maven
  
 6. PDF Generation: iText

  | Folder / File                                                       | Description                                   |
  | ------------------------------------------------------------------- | --------------------------------------------- |
  | `src/main/java/com/payroll/payrollauth/config/`                     | Security, JWT, and application configurations |
  | `src/main/java/com/payroll/payrollauth/controller/`                 | REST API Controllers (Endpoints)              |
  | `src/main/java/com/payroll/payrollauth/entity/`                     | JPA Database Entities                         |
  | `src/main/java/com/payroll/payrollauth/model/`                      | DTOs / Request/Response objects               |
  | `src/main/java/com/payroll/payrollauth/repository/`                 | Spring Data JPA Repositories                  |
  | `src/main/java/com/payroll/payrollauth/service/`                    | Business logic and services                   |
  | `src/main/java/com/payroll/payrollauth/PayrollAuthApplication.java` | Main Spring Boot Application entry point      |

    # JWT Secret
    jwt.secret=your_secret_key
    jwt.expiration=86400000

  | Method | Endpoint         | Description     | Access     |
  | ------ | ---------------- | --------------- | ---------- |
  | POST   | `/employee`      | Add Employee    | Admin only |
  | GET    | `/employee/{id}` | Get Employee    | Self/Admin |
  | PUT    | `/employee/{id}` | Update Employee | Self/Admin |
  | DELETE | `/employee/{id}` | Delete Employee | Admin only |

  
  | Method | Endpoint               | Description             | Access           |
  | ------ | ---------------------- | ----------------------- | ---------------- |
  | POST   | `/salary`              | Create Salary           | Admin only       |
  | GET    | `/salary/{employeeId}` | Get Salary for Employee | Admin/Accountant |
  | PUT    | `/salary/{id}`         | Update Salary           | Admin only       |
  | DELETE | `/salary/{id}`         | Delete Salary Record    | Admin only       |

  | Method | Endpoint              | Description         | Access         |
  | ------ | --------------------- | ------------------- | -------------- |
  | POST   | `/leave/apply`        | Apply Leave         | Employee       |
  | GET    | `/leave/{employeeId}` | Get Employee Leaves | Employee/Admin |
  | PUT    | `/leave/{id}/approve` | Approve Leave       | Admin/Manager  |
  | PUT    | `/leave/{id}/reject`  | Reject Leave        | Admin/Manager  |


  | Method | Endpoint                        | Description              | Access   |
  | ------ | ------------------------------- | ------------------------ | -------- |
  | GET    | `/payslip/{employeeId}/{month}` | Download Monthly Payslip | Employee |

  
  | Role           | Permissions                                                     |
  | -------------- | --------------------------------------------------------------- |
  | **ADMIN**      | Full access: Manage employees, salaries, leaves, approve/reject |
  | **USER**       | View own profile, apply for leaves                              |
  | **ACCOUNTANT** | View salaries, generate reports/payslips                        |

  # Build
  mvn clean install
  
  # Run
  mvn spring-boot:run






