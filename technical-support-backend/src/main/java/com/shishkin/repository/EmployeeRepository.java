package com.shishkin.repository;

import com.shishkin.domain.employee.Employee;
import com.shishkin.domain.employee.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByLogin(String login);
    List<Employee> findAllByRole(Role role);
}
