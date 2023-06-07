package com.shishkin.service;

import com.shishkin.domain.device.DeviceType;
import com.shishkin.dto.models.DeviceDto;

import java.util.List;

public interface DeviceService {
    List<DeviceType> getTypes();
    List<DeviceDto> getAll();

    List<DeviceDto> getByOwnerId(Long id);

    DeviceDto getBySerialNumber(Long serialNumber);

    DeviceDto updateCondition(DeviceDto deviceDto);

    DeviceDto updateOwner(DeviceDto deviceDto);
}
