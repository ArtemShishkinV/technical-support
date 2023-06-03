package com.shishkin.service.impl;

import com.shishkin.domain.application.Application;
import com.shishkin.domain.application.enums.ApplicationObjectType;
import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.domain.application.enums.ApplicationStatus;
import com.shishkin.domain.application.software.ApplicationSoftware;
import com.shishkin.dto.application.ApplicationDto;
import com.shishkin.mapper.ApplicationDeviceMapper;
import com.shishkin.mapper.ApplicationSoftwareMapper;
import com.shishkin.repository.ApplicationDeviceRepository;
import com.shishkin.repository.ApplicationRepository;
import com.shishkin.repository.ApplicationSoftwareRepository;
import com.shishkin.repository.StatusRepository;
import com.shishkin.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.val;
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
        return getApplicationsDtoByStatusTitles(ApplicationStatus.NEW.getTitle());
    }

    @Override
    public List<ApplicationDto> findAllActive() {
        return getApplicationsDtoByStatusTitles(ApplicationStatus.ACTIVE.getTitle());
    }

    @Override
    public List<ApplicationDto> findAllArchive() {
        return getApplicationsDtoByStatusTitles(
                ApplicationStatus.SOLVED.getTitle(),
                ApplicationStatus.CANCELLED.getTitle());
    }

    @Override
    public ApplicationDto findByCategoryAndApplicationId(String category, Long applicationId) {
        ApplicationObjectType applicationObjectType = ApplicationObjectType.valueOf(category.toUpperCase());
        if (ApplicationObjectType.SOFTWARE.equals(applicationObjectType)) {
            return applicationSoftwareMapper.valueOf(applicationSoftwareRepository.findByApplicationId(applicationId));
        }
        return applicationDeviceMapper.valueOf(applicationDeviceRepository.findByApplicationId(applicationId));
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

    private List<ApplicationDto> getApplicationsDtoByStatusTitles(String... statusTitles) {
        val applicationDeviceList = applicationDeviceRepository.findAllByStatusTitles(statusTitles);
        val applicationSoftwareList = applicationSoftwareRepository.findAllByStatusTitles(statusTitles);
        return combineApplications(applicationDeviceList, applicationSoftwareList);
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
