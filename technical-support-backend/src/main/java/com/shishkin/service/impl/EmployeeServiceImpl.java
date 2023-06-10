package com.shishkin.service.impl;

import com.shishkin.dto.employee.EmployeeCreateModelsDto;
import com.shishkin.dto.employee.EmployeeDto;
import com.shishkin.exception.EmployeeNotFoundException;
import com.shishkin.repository.EmployeeRepository;
import com.shishkin.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeDto::new)
                .toList();
    }

    @Override
    public EmployeeDto findByEmail(String email) throws EmployeeNotFoundException {
        log.debug("#find employee with email: " + email);
        return new EmployeeDto(employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        HttpStatus.NOT_FOUND,
                        "Employee with email " + email + " not found!")));
    }

    @Override
    public EmployeeDto findById(long id) throws EmployeeNotFoundException {
        log.debug("#find employee with id: " + id);
        return new EmployeeDto(employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        HttpStatus.NOT_FOUND,
                        "Employee with id " + id + " not found!")));
    }

    @Override
    public EmployeeCreateModelsDto getModels() {
        return null;
    }
}
