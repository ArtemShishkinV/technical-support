package com.shishkin.service;

import com.shishkin.dto.employee.EmployeeCreateModelsDto;
import com.shishkin.dto.employee.EmployeeDto;
import com.shishkin.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll();

    EmployeeDto findById(long id) throws EmployeeNotFoundException;

    EmployeeDto findByEmail(String email) throws EmployeeNotFoundException;

    EmployeeCreateModelsDto getModels();
}
