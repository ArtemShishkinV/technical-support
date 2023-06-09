package com.shishkin.repository;

import com.shishkin.domain.employee.Employee;
import com.shishkin.domain.employee.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByRole(Role role);

    Optional<Employee> findByEmail(String email);
}
