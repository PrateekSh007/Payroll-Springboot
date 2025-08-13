package com.Payroll.PayrollAuth.repository;

import com.Payroll.PayrollAuth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existByUsername(String username);
    boolean existByEmail(String email);
}
