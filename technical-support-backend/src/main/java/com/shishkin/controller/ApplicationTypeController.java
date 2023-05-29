package com.shishkin.controller;

import com.shishkin.domain.application.device.ApplicationDeviceType;
import com.shishkin.domain.application.software.ApplicationSoftware;
import com.shishkin.domain.application.software.ApplicationSoftwareType;
import com.shishkin.repository.ApplicationDeviceTypeRepository;
import com.shishkin.repository.ApplicationSoftwareTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/application-type")
public class ApplicationTypeController {
    private final ApplicationDeviceTypeRepository applicationDeviceTypeRepository;
    private final ApplicationSoftwareTypeRepository applicationSoftwareTypeRepository;
    @GetMapping("/device")
    public List<ApplicationDeviceType> getApplicationDeviceTypes() {
        return applicationDeviceTypeRepository.findAll();
    }

    @GetMapping("/software")
    public List<ApplicationSoftwareType> getApplicationSoftwareTypes() {
        return applicationSoftwareTypeRepository.findAll();
    }
}
