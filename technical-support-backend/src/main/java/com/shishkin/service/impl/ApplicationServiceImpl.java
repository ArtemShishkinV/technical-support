package com.shishkin.service.impl;

import com.shishkin.domain.application.Application;
import com.shishkin.domain.application.ApplicationObjectType;
import com.shishkin.domain.application.Priority;
import com.shishkin.domain.application.Status;
import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.domain.application.software.ApplicationSoftware;
import com.shishkin.domain.employee.Employee;
import com.shishkin.dto.application.ApplicationCreatedDto;
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
import com.shishkin.service.utils.AppointmentDatetimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationDeviceMapper applicationDeviceMapper;
    private final ApplicationSoftwareMapper applicationSoftwareMapper;
    private final ApplicationDeviceRepository applicationDeviceRepository;
    private final ApplicationSoftwareRepository applicationSoftwareRepository;
    private final EmployeeRepository employeeRepository;

    private final PriorityRepository priorityRepository;

    private final StatusRepository statusRepository;

    private final SoftwareRepository softwareRepository;
    private final ApplicationSoftwareTypeRepository applicationSoftwareTypeRepository;
    private final ApplicationRepository applicationRepository;

    private final DeviceRepository deviceRepository;
    private final ApplicationDeviceTypeRepository applicationDeviceTypeRepository;

    private final ApplicationAssigneeService applicationAssigneeService;
    private final ApplicationTypeService applicationTypeService;

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
    public ApplicationDto create(ApplicationCreatedDto applicationCreatedDto) {
        Application application = createBaseApplication(applicationCreatedDto);
        return createApplicationByType(application, applicationCreatedDto);
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

    private Application createBaseApplication(ApplicationCreatedDto applicationCreatedDto) {
        LocalDateTime createdAt = LocalDateTime.now();
        Priority priority = priorityRepository.findByTitle(applicationCreatedDto.getPriority());
        Status status = statusRepository.findByTitle("Создана");
        Employee initiator = employeeRepository.getById(applicationCreatedDto.getInitiator().getStaffNumber());

        return Application.builder()
                .solvedAt(null)
                .priority(priority)
                .status(status)
                .description(applicationCreatedDto.getDescription())
                .initiator(initiator)
                .executor(applicationAssigneeService.getExecutor(applicationCreatedDto))
                .appointmentAt(AppointmentDatetimeUtils.getAppointmentAt(priority, createdAt))
                .createdAt(createdAt)
                .build();
    }

    private ApplicationDto createApplicationByType(Application application,
                                                   ApplicationCreatedDto applicationCreatedDto) {
        if (ApplicationObjectType.DEVICE.getTitle().equals(applicationCreatedDto.getCategory())) {
            ApplicationDevice applicationDevice = createDeviceApplication(application, applicationCreatedDto);
            return applicationDeviceMapper.valueOf(applicationTypeService.save(applicationDevice));
        }
        ApplicationSoftware applicationSoftware = createSoftwareApplication(application, applicationCreatedDto);
        return applicationSoftwareMapper.valueOf(applicationTypeService.save(applicationSoftware));
    }

    private ApplicationSoftware createSoftwareApplication(Application application,
                                                          ApplicationCreatedDto applicationCreatedDto) {
        String type = applicationCreatedDto.getType();
        return ApplicationSoftware.builder()
                .application(application)
                .software(softwareRepository.getById(applicationCreatedDto.getApplicationObjectDto().getId()))
                .applicationSoftwareType(applicationSoftwareTypeRepository.findByTitle(type))
                .build();
    }

    private ApplicationDevice createDeviceApplication(Application application,
                                                      ApplicationCreatedDto applicationCreatedDto) {
        String type = applicationCreatedDto.getType();
        ApplicationDevice.ApplicationDeviceBuilder builder = ApplicationDevice.builder()
                .application(application)
                .applicationDeviceType(applicationDeviceTypeRepository.findByTitle(type));
        return builder.device(deviceRepository.getById(applicationCreatedDto.getApplicationObjectDto().getId())).build();
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
