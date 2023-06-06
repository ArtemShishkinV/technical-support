package com.shishkin.service.impl.application.create;

import com.shishkin.domain.application.base.Application;
import com.shishkin.domain.application.base.Priority;
import com.shishkin.domain.application.base.Status;
import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.domain.application.enums.ApplicationObjectType;
import com.shishkin.domain.application.software.ApplicationSoftware;
import com.shishkin.domain.application.software.ApplicationSoftwareType;
import com.shishkin.domain.employee.Employee;
import com.shishkin.dto.application.ApplicationCreateRequestDto;
import com.shishkin.dto.application.ApplicationDto;
import com.shishkin.mapper.ApplicationDeviceMapper;
import com.shishkin.mapper.ApplicationSoftwareMapper;
import com.shishkin.repository.ApplicationDeviceTypeRepository;
import com.shishkin.repository.ApplicationSoftwareRepository;
import com.shishkin.repository.ApplicationSoftwareTypeRepository;
import com.shishkin.repository.DeviceRepository;
import com.shishkin.repository.EmployeeRepository;
import com.shishkin.repository.PriorityRepository;
import com.shishkin.repository.SoftwareRepository;
import com.shishkin.repository.StatusRepository;
import com.shishkin.service.ApplicationTypeService;
import com.shishkin.service.utils.AppointmentDatetimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
class ApplicationCreator {
    private final PriorityRepository priorityRepository;
    private final StatusRepository statusRepository;
    private final EmployeeRepository employeeRepository;
    private final SoftwareRepository softwareRepository;
    private final ApplicationSoftwareTypeRepository applicationSoftwareTypeRepository;
    private final DeviceRepository deviceRepository;
    private final ApplicationDeviceTypeRepository applicationDeviceTypeRepository;

    private final ApplicationDeviceMapper applicationDeviceMapper;
    private final ApplicationTypeService applicationTypeService;
    private final ApplicationSoftwareMapper applicationSoftwareMapper;

    public Application valueOf(ApplicationCreateRequestDto applicationCreateRequestDto,
                               Employee executor) {
        LocalDateTime createdAt = LocalDateTime.now();
        Priority priority = priorityRepository.findByTitle(applicationCreateRequestDto.getPriority());
        Status status = statusRepository.findByTitle("Создана");
        Employee initiator = employeeRepository.getById(applicationCreateRequestDto.getInitiator().getStaffNumber());


        return Application.builder()
                .solvedAt(null)
                .priority(priority)
                .status(status)
                .description(applicationCreateRequestDto.getDescription())
                .initiator(initiator)
                .isOffline(applicationCreateRequestDto.isOffline())
                .executor(executor)
                .appointmentAt(AppointmentDatetimeUtils.getAppointmentAt(priority, createdAt))
                .createdAt(createdAt)
                .build();
    }

    public ApplicationDto valueOf(Application application,
                                  ApplicationCreateRequestDto applicationCreateRequestDto) {
        if (ApplicationObjectType.DEVICE.getTitle().equals(applicationCreateRequestDto.getCategory())) {
            ApplicationDevice applicationDevice = createDeviceApplication(application, applicationCreateRequestDto);
            return applicationDeviceMapper.valueOf(applicationTypeService.save(applicationDevice));
        }
        ApplicationSoftware applicationSoftware = createSoftwareApplication(application, applicationCreateRequestDto);
        return applicationSoftwareMapper.valueOf(applicationTypeService.save(applicationSoftware));
    }

    private ApplicationDevice createDeviceApplication(Application application,
                                                      ApplicationCreateRequestDto applicationCreateRequestDto) {
        String type = applicationCreateRequestDto.getType();
        ApplicationDevice.ApplicationDeviceBuilder builder = ApplicationDevice.builder()
                .application(application)
                .applicationDeviceType(applicationDeviceTypeRepository.findByTitle(type));
        return builder.device(deviceRepository.getById(applicationCreateRequestDto.getApplicationObjectId())).build();
    }

    private ApplicationSoftware createSoftwareApplication(Application application,
                                        ApplicationCreateRequestDto applicationCreateRequestDto) {
        String type = applicationCreateRequestDto.getType();
        return ApplicationSoftware.builder()
                .application(application)
                .software(softwareRepository.getById(applicationCreateRequestDto.getApplicationObjectId()))
                .applicationSoftwareType(applicationSoftwareTypeRepository.findByTitle(type))
                .build();
    }
}
