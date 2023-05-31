package com.shishkin.service;

import com.shishkin.domain.device.DeviceType;
import com.shishkin.dto.DeviceDto;

import java.util.List;

public interface DeviceService {
    List<DeviceType> getTypes();
    List<DeviceDto> getAll();

    List<DeviceDto> getById(Long id);
}
