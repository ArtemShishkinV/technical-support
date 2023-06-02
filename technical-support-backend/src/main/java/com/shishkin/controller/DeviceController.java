package com.shishkin.controller;

import com.shishkin.domain.device.DeviceType;
import com.shishkin.dto.DeviceDto;
import com.shishkin.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/device")
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping("/types")
    public List<DeviceType> getDeviceTypes() {
        return deviceService.getTypes();
    }

    @GetMapping
    public List<DeviceDto> getAll() {
        return deviceService.getAll();
    }

    @GetMapping("/owner/{id}")
    public List<DeviceDto> getById(@PathVariable Long id) {
        return deviceService.getByOwnerId(id);
    }

    @GetMapping("/{serialNumber}")
    public DeviceDto getBySerialNumber(@PathVariable Long serialNumber) {
        return deviceService.getBySerialNumber(serialNumber);
    }
}
