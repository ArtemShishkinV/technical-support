package com.shishkin.service.impl;

import com.shishkin.domain.application.Application;
import com.shishkin.domain.application.enums.ApplicationObjectType;
import com.shishkin.domain.application.Priority;
import com.shishkin.domain.application.Status;
import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.domain.application.software.ApplicationSoftware;
import com.shishkin.domain.employee.Employee;
import com.shishkin.dto.NotificationDto;
import com.shishkin.dto.application.ApplicationCreateModelsDto;
import com.shishkin.dto.application.ApplicationCreatedDto;
import com.shishkin.dto.application.ApplicationDto;
import com.shishkin.dto.application.ApplicationObjectDto;
import com.shishkin.mapper.ApplicationDeviceMapper;
import com.shishkin.mapper.ApplicationSoftwareMapper;
import com.shishkin.repository.ApplicationDeviceTypeRepository;
import com.shishkin.repository.ApplicationSoftwareTypeRepository;
import com.shishkin.repository.DeviceRepository;
import com.shishkin.repository.DeviceTypeRepository;
import com.shishkin.repository.EmployeeRepository;
import com.shishkin.repository.PriorityRepository;
import com.shishkin.repository.SoftwareRepository;
import com.shishkin.repository.SoftwareTypeRepository;
import com.shishkin.repository.StatusRepository;
import com.shishkin.service.ApplicationAssigneeService;
import com.shishkin.service.ApplicationCreateService;
import com.shishkin.service.ApplicationTypeService;
import com.shishkin.service.NotificationService;
import com.shishkin.service.utils.AppointmentDatetimeUtils;
import com.sun.jersey.json.impl.provider.entity.JSONArrayProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationCreateServiceImpl implements ApplicationCreateService {
    private final NotificationService notificationService;
    private final PriorityRepository priorityRepository;
    private final StatusRepository statusRepository;
    private final EmployeeRepository employeeRepository;
    private final ApplicationAssigneeService applicationAssigneeService;
    private final ApplicationDeviceMapper applicationDeviceMapper;
    private final ApplicationTypeService applicationTypeService;
    private final ApplicationSoftwareMapper applicationSoftwareMapper;
    private final SoftwareRepository softwareRepository;
    private final ApplicationSoftwareTypeRepository applicationSoftwareTypeRepository;
    private final ApplicationDeviceTypeRepository applicationDeviceTypeRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceTypeRepository deviceTypeRepository;
    private final SoftwareTypeRepository softwareTypeRepository;

    @Override
    public ApplicationDto create(ApplicationCreatedDto applicationCreatedDto) {
        Application application = createBaseApplication(applicationCreatedDto);
        ApplicationDto applicationDto = createApplicationByType(application, applicationCreatedDto);
        sendNotification(applicationDto);
        return applicationDto;
    }

    private void sendNotification(ApplicationDto application) {
        notificationService.sendNotification(application);
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
                .software(softwareRepository.getById(applicationCreatedDto.getApplicationObjectId()))
                .applicationSoftwareType(applicationSoftwareTypeRepository.findByTitle(type))
                .build();
    }

    private ApplicationDevice createDeviceApplication(Application application,
                                                      ApplicationCreatedDto applicationCreatedDto) {
        String type = applicationCreatedDto.getType();
        ApplicationDevice.ApplicationDeviceBuilder builder = ApplicationDevice.builder()
                .application(application)
                .applicationDeviceType(applicationDeviceTypeRepository.findByTitle(type));
        return builder.device(deviceRepository.getById(applicationCreatedDto.getApplicationObjectId())).build();
    }


    @Override
    public ApplicationCreateModelsDto getModels(Long initiatorId) {
        Employee employee = employeeRepository.getById(initiatorId);
        List<ApplicationObjectDto> availableDevices = getDevicesAsApplicationObjectDtoByOwner(null);
        List<ApplicationObjectDto> myDevices = getDevicesAsApplicationObjectDtoByOwner(employee);
        List<ApplicationObjectDto> software = softwareRepository.findAll().stream().map(ApplicationObjectDto::new).toList();
        return ApplicationCreateModelsDto.builder()
                .priorities(priorityRepository.findAll())
                .availableDevices(availableDevices)
                .myDevices(myDevices)
                .deviceTypes(deviceTypeRepository.findAll())
                .softwareTypes(softwareTypeRepository.findAll())
                .softwares(software)
                .applicationDeviceTypes(applicationDeviceTypeRepository.findAll())
                .applicationSoftwareTypes(applicationSoftwareTypeRepository.findAll())
                .build();
    }

    private List<ApplicationObjectDto> getDevicesAsApplicationObjectDtoByOwner(Employee owner) {
        return deviceRepository.findAllByOwner(owner).stream().map(ApplicationObjectDto::new).toList();
    }
}
