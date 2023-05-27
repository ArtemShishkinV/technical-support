package com.shishkin.repository;

import com.shishkin.domain.application.Application;
import com.shishkin.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByExecutor(Employee employee);
}
