package com.shishkin.service.impl;

import com.shishkin.domain.application.Application;
import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.domain.application.software.ApplicationSoftware;
import com.shishkin.repository.ApplicationDeviceRepository;
import com.shishkin.repository.ApplicationRepository;
import com.shishkin.repository.ApplicationSoftwareRepository;
import com.shishkin.service.ApplicationTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ApplicationTypeServiceImpl implements ApplicationTypeService {
    private final ApplicationDeviceRepository applicationDeviceRepository;
    private final ApplicationSoftwareRepository applicationSoftwareRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public ApplicationDevice save(ApplicationDevice applicationDevice) {
        Application application = applicationRepository.save(applicationDevice.getApplication());
        return applicationDeviceRepository.save(
                ApplicationDevice.builder()
                        .application(application)
                        .applicationDeviceType(applicationDevice.getApplicationDeviceType())
                        .device(applicationDevice.getDevice())
                        .build()
        );
    }

    @Override
    @Transactional
    public ApplicationSoftware save(ApplicationSoftware applicationSoftware) {
        Application application = applicationRepository.save(applicationSoftware.getApplication());
        return applicationSoftwareRepository.save(
                ApplicationSoftware.builder()
                        .application(application)
                        .applicationSoftwareType(applicationSoftware.getApplicationSoftwareType())
                        .software(applicationSoftware.getSoftware())
                        .build()
        );
    }
}
