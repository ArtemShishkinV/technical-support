package com.shishkin.mapper;

import com.shishkin.domain.application.ApplicationObjectType;
import com.shishkin.domain.application.software.ApplicationSoftware;
import com.shishkin.dto.application.ApplicationDto;
import com.shishkin.dto.application.ApplicationObjectDto;
import com.shishkin.dto.application.BasedApplicationDto;
import com.shishkin.repository.ApplicationRepository;
import com.shishkin.repository.ApplicationSoftwareTypeRepository;
import com.shishkin.repository.SoftwareRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ApplicationSoftwareMapper {
    private final ApplicationRepository applicationRepository;
    private final SoftwareRepository softwareRepository;
    private final ApplicationSoftwareTypeRepository applicationSoftwareTypeRepository;

    public ApplicationSoftware valueOf(ApplicationDto applicationDto) {
        return ApplicationSoftware.builder()
                .application(applicationRepository.getById(applicationDto.getBasedApplicationDto().getId()))
                .software(softwareRepository.getById(applicationDto.getApplicationObjectDto().getId()))
                .applicationSoftwareType(applicationSoftwareTypeRepository.findByTitle(applicationDto.getType()))
                .build();
    }

    public ApplicationDto valueOf(ApplicationSoftware applicationSoftware) {
        return ApplicationDto.builder()
                .basedApplicationDto(new BasedApplicationDto(applicationSoftware.getApplication()))
                .category(ApplicationObjectType.SOFTWARE.getTitle())
                .applicationObjectDto(new ApplicationObjectDto(applicationSoftware.getSoftware()))
                .type(applicationSoftware.getApplicationSoftwareType().getTitle())
                .build();

    }
}
