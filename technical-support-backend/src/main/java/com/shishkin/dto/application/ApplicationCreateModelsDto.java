package com.shishkin.dto.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.domain.application.base.Priority;
import com.shishkin.domain.application.device.ApplicationDeviceType;
import com.shishkin.domain.application.software.ApplicationSoftwareType;
import com.shishkin.domain.device.DeviceType;
import com.shishkin.domain.software.SoftwareType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class ApplicationCreateModelsDto {
    List<Priority> priorities;
    List<ApplicationDeviceType> applicationDeviceTypes;
    List<ApplicationSoftwareType> applicationSoftwareTypes;
    List<DeviceType> deviceTypes;
    List<ApplicationObjectDto> myDevices;
    List<ApplicationObjectDto> availableDevices;
    List<SoftwareType> softwareTypes;
    List<ApplicationObjectDto> softwares;
}
