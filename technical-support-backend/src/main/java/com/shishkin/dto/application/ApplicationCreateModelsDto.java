package com.shishkin.dto.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.domain.application.Priority;
import com.shishkin.domain.application.device.ApplicationDeviceType;
import com.shishkin.domain.application.software.ApplicationSoftwareType;
import com.shishkin.domain.device.DeviceType;
import com.shishkin.domain.software.Software;
import com.shishkin.domain.software.SoftwareType;
import com.shishkin.dto.DeviceDto;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class ApplicationCreateModelsDto {
    List<Priority> priorities;
    List<ApplicationDeviceType> applicationDeviceTypes;
    List<ApplicationSoftwareType> applicationSoftwareTypes;
    List<DeviceType> deviceTypes;
    List<DeviceDto> devices;
    List<SoftwareType> softwareTypes;
    List<Software> softwares;
}
