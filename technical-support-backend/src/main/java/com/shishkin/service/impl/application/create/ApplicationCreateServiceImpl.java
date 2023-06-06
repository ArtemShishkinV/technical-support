package com.shishkin.service.impl.application.create;

import com.shishkin.domain.application.base.Application;
import com.shishkin.domain.application.enums.ApplicationObjectType;
import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.domain.application.software.ApplicationSoftware;
import com.shishkin.domain.employee.Employee;
import com.shishkin.dto.application.ApplicationCreateModelsDto;
import com.shishkin.dto.application.ApplicationCreateRequestDto;
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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationCreateServiceImpl implements ApplicationCreateService {
    private final ApplicationCreator applicationCreator;
    private final PriorityRepository priorityRepository;
    private final EmployeeRepository employeeRepository;
    private final SoftwareRepository softwareRepository;
    private final ApplicationSoftwareTypeRepository applicationSoftwareTypeRepository;
    private final ApplicationDeviceTypeRepository applicationDeviceTypeRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceTypeRepository deviceTypeRepository;
    private final SoftwareTypeRepository softwareTypeRepository;
    private final NotificationService notificationService;
    private final ApplicationAssigneeService applicationAssigneeService;

    @Override
    public ApplicationDto create(ApplicationCreateRequestDto applicationCreateRequestDto) {
        // Создание базового объекта заявки
        Application application = createBaseApplication(applicationCreateRequestDto);
        // Создание заявки, зависящей от категории
        ApplicationDto applicationDto = applicationCreator.valueOf(application, applicationCreateRequestDto);
        //Отправка уведомления о создании заявки исполнителю
        sendNotification(applicationDto);
        return applicationDto;
    }
    private void sendNotification(ApplicationDto application) {
        notificationService.sendNotification(application);
    }

    private Application createBaseApplication(ApplicationCreateRequestDto applicationCreateRequestDto) {
        Employee executor = applicationAssigneeService.getExecutor(applicationCreateRequestDto);
        return applicationCreator.valueOf(applicationCreateRequestDto, executor);
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
