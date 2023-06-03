package com.shishkin.mapper;

import com.shishkin.domain.application.enums.ApplicationObjectType;
import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.dto.application.ApplicationDto;
import com.shishkin.dto.application.ApplicationObjectDto;
import com.shishkin.dto.application.BasedApplicationDto;
import com.shishkin.repository.ApplicationDeviceTypeRepository;
import com.shishkin.repository.ApplicationRepository;
import com.shishkin.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ApplicationDeviceMapper {
    private final ApplicationRepository applicationRepository;
    private final DeviceRepository deviceRepository;
    private final ApplicationDeviceTypeRepository applicationDeviceTypeRepository;

    public ApplicationDevice valueOf(ApplicationDto applicationDto) {
        return ApplicationDevice.builder()
                .application(applicationRepository.getById(applicationDto.getBasedApplicationDto().getId()))
                .device(deviceRepository.getById(applicationDto.getApplicationObjectDto().getId()))
                .applicationDeviceType(applicationDeviceTypeRepository.findByTitle(applicationDto.getType()))
                .build();
    }

    public ApplicationDto valueOf(ApplicationDevice applicationDevice) {
        return ApplicationDto.builder()
                .basedApplicationDto(new BasedApplicationDto(applicationDevice.getApplication()))
                .category(ApplicationObjectType.DEVICE.getTitle())
                .applicationObjectDto(new ApplicationObjectDto(applicationDevice.getDevice()))
                .type(applicationDevice.getApplicationDeviceType().getTitle())
                .build();

    }
}
