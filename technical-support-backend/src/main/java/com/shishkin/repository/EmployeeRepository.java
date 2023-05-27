package com.shishkin.repository;

import com.shishkin.domain.employee.Employee;
import com.shishkin.domain.employee.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByLogin(String login);
    List<Employee> findAllByRole(Role role);
    List<Employee> findAllByRoleAndOnline(Role role, boolean online);
}
