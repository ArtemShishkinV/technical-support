package com.shishkin.mapper;

import com.shishkin.domain.device.Device;
import com.shishkin.dto.DeviceDto;
import com.shishkin.dto.employee.EmployeeDto;
import com.shishkin.repository.DeviceRepository;
import com.shishkin.repository.DeviceTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeviceMapper {
    private final DeviceRepository deviceRepository;

    public DeviceDto valueOf(Device device) {
        return DeviceDto.builder()
                .id(device.getSerialNumber())
                .title(device.getTitle())
                .description(device.getDescription())
                .type(device.getDeviceType().getTitle())
                .condition(device.getDeviceCondition().getTitle())
                .owner(new EmployeeDto(device.getOwner()))
                .issuedAt(device.getIssuedAt())
                .icon(device.getDeviceType().getIcon())
                .build();
    }

    public Device valueOf(DeviceDto dto) {
        return deviceRepository.getById(dto.getId());
    }
}
