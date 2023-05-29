package com.shishkin.repository;

import com.shishkin.domain.device.Device;
import com.shishkin.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Set<Device> findAllByOwner(Employee employee);
}
