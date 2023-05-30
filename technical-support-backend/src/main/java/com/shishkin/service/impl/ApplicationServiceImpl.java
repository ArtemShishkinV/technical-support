package com.shishkin.service.impl;

import com.shishkin.domain.application.Application;
import com.shishkin.domain.application.ApplicationObjectType;
import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.domain.application.software.ApplicationSoftware;
import com.shishkin.dto.application.ApplicationDto;
import com.shishkin.mapper.ApplicationDeviceMapper;
import com.shishkin.mapper.ApplicationSoftwareMapper;
import com.shishkin.repository.ApplicationDeviceRepository;
import com.shishkin.repository.ApplicationDeviceTypeRepository;
import com.shishkin.repository.ApplicationRepository;
import com.shishkin.repository.ApplicationSoftwareRepository;
import com.shishkin.repository.ApplicationSoftwareTypeRepository;
import com.shishkin.repository.DeviceRepository;
import com.shishkin.repository.EmployeeRepository;
import com.shishkin.repository.PriorityRepository;
import com.shishkin.repository.SoftwareRepository;
import com.shishkin.repository.StatusRepository;
import com.shishkin.service.ApplicationAssigneeService;
import com.shishkin.service.ApplicationService;
import com.shishkin.service.ApplicationTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationDeviceMapper applicationDeviceMapper;
    private final ApplicationSoftwareMapper applicationSoftwareMapper;
    private final ApplicationDeviceRepository applicationDeviceRepository;
    private final ApplicationSoftwareRepository applicationSoftwareRepository;

    private final StatusRepository statusRepository;
    private final ApplicationRepository applicationRepository;


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

    @Override
    public ApplicationDto findByCategoryAndId(String category, Long id) {
        ApplicationObjectType applicationObjectType = ApplicationObjectType.valueOf(category.toUpperCase());
        if (ApplicationObjectType.SOFTWARE.equals(applicationObjectType)) {
            return applicationSoftwareMapper.valueOf(applicationSoftwareRepository.findByApplicationId(id));
        }
        return applicationDeviceMapper.valueOf(applicationDeviceRepository.findByApplicationId(id));
    }

    @Override
    public ApplicationDto changeStatus(ApplicationDto applicationDto) {
        var basedDto = applicationDto.getBasedApplicationDto();
        var status = statusRepository.findByTitle(basedDto.getStatus());
        Application application = applicationRepository.getById(basedDto.getId());
        application.setStatus(status);
        applicationRepository.save(application);
        return applicationDto;
    }

    private List<ApplicationDto> combineApplications(List<ApplicationDevice> applicationsDevices,
                                                     List<ApplicationSoftware> applicationsSoftware) {
        var applicationsDto = applicationsDevices.stream().map(applicationDeviceMapper::valueOf).toList();
        var anotherApplicationsDto = applicationsSoftware.stream().map(applicationSoftwareMapper::valueOf).toList();

        var result = new ArrayList<>(applicationsDto);
        result.addAll(anotherApplicationsDto);

        return result;
    }
}
