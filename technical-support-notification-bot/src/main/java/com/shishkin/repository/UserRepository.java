package com.shishkin.repository;

import com.shishkin.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> getByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}
