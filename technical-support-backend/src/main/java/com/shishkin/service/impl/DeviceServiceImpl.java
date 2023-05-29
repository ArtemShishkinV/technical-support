package com.shishkin.service.impl;

import com.shishkin.domain.device.DeviceType;
import com.shishkin.dto.DeviceDto;
import com.shishkin.mapper.DeviceMapper;
import com.shishkin.repository.DeviceRepository;
import com.shishkin.repository.DeviceTypeRepository;
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
}
