package com.shishkin.service.impl;

import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.domain.application.software.ApplicationSoftware;
import com.shishkin.dto.application.ApplicationDto;
import com.shishkin.repository.ApplicationDeviceRepository;
import com.shishkin.repository.ApplicationSoftwareRepository;
import com.shishkin.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationDeviceRepository applicationDeviceRepository;
    private final ApplicationSoftwareRepository applicationSoftwareRepository;

    @Override
    public List<ApplicationDto> findAllNew() {
        var allNewDevice = applicationDeviceRepository.findAllNew();
        var allNewSoftware = applicationSoftwareRepository.findAllNew();
        return combineApplications(allNewDevice, allNewSoftware);
    }

    @Override
    public List<ApplicationDto> findAllActive() {
        var allActiveDevice = applicationDeviceRepository.findAllActive();
        var allActiveSoftware = applicationSoftwareRepository.findAllActive();
        return combineApplications(allActiveDevice, allActiveSoftware);
    }

    @Override
    public List<ApplicationDto> findAllArchive() {
        var allArchiveDevice = applicationDeviceRepository.findAllArchive();
        var allArchiveSoftware = applicationSoftwareRepository.findAllArchive();
        return combineApplications(allArchiveDevice, allArchiveSoftware);
    }

    private List<ApplicationDto> combineApplications(List<ApplicationDevice> applicationsDevices,
                                                     List<ApplicationSoftware> applicationsSoftware) {
        var applicationsDto = applicationsDevices.stream().map(ApplicationDto::new).toList();
        var anotherApplicationsDto = applicationsSoftware.stream().map(ApplicationDto::new).toList();

        var result = new ArrayList<>(applicationsDto);
        result.addAll(anotherApplicationsDto);

        return result;
    }
}
