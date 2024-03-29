package com.shishkin.service.impl;

import com.shishkin.domain.device.Device;
import com.shishkin.domain.device.DeviceCondition;
import com.shishkin.domain.device.DeviceType;
import com.shishkin.domain.employee.Employee;
import com.shishkin.dto.models.DeviceDto;
import com.shishkin.mapper.DeviceMapper;
import com.shishkin.repository.DeviceConditionRepository;
import com.shishkin.repository.DeviceRepository;
import com.shishkin.repository.DeviceTypeRepository;
import com.shishkin.repository.EmployeeRepository;
import com.shishkin.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceMapper deviceMapper;
    private final DeviceTypeRepository deviceTypeRepository;
    private final DeviceRepository deviceRepository;
    private final EmployeeRepository employeeRepository;
    private final DeviceConditionRepository deviceConditionRepository;

    @Override
    public List<DeviceType> getTypes() {
        return deviceTypeRepository.findAll();
    }

    @Override
    public List<DeviceDto> getAll() {
        return deviceRepository.findAll()
                .stream()
                .map(deviceMapper::valueOf)
                .toList();
    }

    @Override
    public List<DeviceDto> getByOwnerId(Long id) {
        Employee employee = employeeRepository.getById(id);
        return deviceRepository.findAllByOwner(employee)
                .stream()
                .map(deviceMapper::valueOf)
                .toList();
    }

    @Override
    public DeviceDto getBySerialNumber(Long serialNumber) {
        return deviceMapper.valueOf(deviceRepository.getById(serialNumber));
    }

    @Override
    public DeviceDto updateCondition(DeviceDto deviceDto) {
        Device device = deviceMapper.valueOf(deviceDto);
        Employee newOwner = employeeRepository.getById(deviceDto.getOwner().getStaffNumber());
        device.setOwner(newOwner);
        return updateAndGetDto(device);
    }

    @Override
    public DeviceDto updateOwner(DeviceDto deviceDto) {
        Device device = deviceMapper.valueOf(deviceDto);
        DeviceCondition deviceCondition = deviceConditionRepository.findByTitle(deviceDto.getCondition());
        device.setDeviceCondition(deviceCondition);
        return updateAndGetDto(device);
    }

    private DeviceDto updateAndGetDto(Device device) {
        return deviceMapper.valueOf(deviceRepository.save(device));
    }
}
