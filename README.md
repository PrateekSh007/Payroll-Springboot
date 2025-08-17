Payroll Management System
A Spring Boot based Payroll Management System with authentication, role-based access, employee management, salary, leave management, and payslip generation. This project is built to demonstrate a basic-to-moderate microservice-style architecture with JWT authentication.

🚀 Features
Authentication & Authorization: Secure registration and login using JSON Web Tokens (JWT).

Role-Based Access Control: Pre-defined roles (Admin, User, Accountant) with specific permissions.

Employee Management: Admins can perform full CRUD (Create, Read, Update, Delete) operations on employees.

Salary Management: Admins can manage salary structures, including base pay, allowances, and deductions.

Leave Management: Employees can apply for leave, which managers or admins can approve or reject.

Payslip Generation: Automatically generate monthly payslips in PDF format, detailing salary, leaves, and deductions.

🛠️ Tech Stack
Category

Technology

Backend

Spring Boot 3, Spring Security 6

Authentication

JSON Web Tokens (JWT)

Database

MySQL (or PostgreSQL)

ORM

Hibernate / Spring Data JPA

Build Tool

Maven

PDF Generation

iText

📁 Project Structure
Here is the core directory structure for the application:

src/main/java/com/payroll/payrollauth/
├── config/       # Security, JWT, and Application configurations
├── controller/   # REST API Controllers (Endpoints)
├── entity/       # JPA Database Entities
├── model/        # DTOs, Request/Response objects
├── repository/   # Spring Data JPA Repositories
├── service/      # Business logic and services
└── PayrollAuthApplication.java

⚙️ Configuration
Update the src/main/resources/application.properties file with your database credentials and a unique JWT secret.

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/payroll_db
spring.datasource.username=root
spring.datasource.password=yourpassword

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Configuration
jwt.secret=your_super_strong_and_long_secret_key_here
jwt.expiration=86400000 # 24 hours in milliseconds

API Endpoints
🔒 All endpoints require a valid JWT Bearer token in the Authorization header.

👨‍💼 Employee APIs
Method

Endpoint

Description

Access

POST

/employee

Add a new employee

ADMIN

GET

/employee/{id}

Get an employee's details

ADMIN, Self

PUT

/employee/{id}

Update an employee

ADMIN, Self

DELETE

/employee/{id}

Delete an employee

ADMIN

💰 Salary APIs
Method

Endpoint

Description

Access

POST

/salary

Create a salary structure

ADMIN

GET

/salary/{employeeId}

Get salary for an employee

ADMIN, ACCOUNTANT

PUT

/salary/{id}

Update a salary structure

ADMIN

DELETE

/salary/{id}

Delete a salary record

ADMIN

📅 Leave APIs
Method

Endpoint

Description

Access

POST

/leave/apply

Apply for leave

USER (Employee)

GET

/leave/{employeeId}

Get all leaves for an employee

ADMIN, Self

PUT

/leave/{id}/approve

Approve a leave request

ADMIN

PUT

/leave/{id}/reject

Reject a leave request

ADMIN

🧾 Payslip API
Method

Endpoint

Description

Access

GET

/payslip/{employeeId}/{month}

Download a monthly payslip (PDF)

ACCOUNTANT, Self

Roles & Permissions
Role

Permissions

ADMIN

Full access to all resources. Can manage employees, salaries, and leaves.

USER

Can view their own profile, apply for leaves, and download their payslip.

ACCOUNTANT

Can view employee salaries and generate payslips/reports.
