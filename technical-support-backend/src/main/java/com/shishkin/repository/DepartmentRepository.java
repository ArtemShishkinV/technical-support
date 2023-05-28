package com.shishkin.repository;

import com.shishkin.domain.employee.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
