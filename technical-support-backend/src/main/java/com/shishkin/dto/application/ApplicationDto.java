package com.shishkin.dto.application;

import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.domain.application.software.ApplicationSoftware;
import lombok.Value;

@Value
public class ApplicationDto {
    private static final String DEVICE_CATEGORY = "Заявка на технику";
    private static final String SOFTWARE_CATEGORY = "Заявка на ПО";

    BasedApplicationDto basedApplicationDto;
    String category;
    ApplicationObjectDto applicationObjectDto;
    String type;

    public ApplicationDto(ApplicationDevice applicationDevice) {
        this.basedApplicationDto = new BasedApplicationDto(applicationDevice.getApplication());
        this.category = DEVICE_CATEGORY;
        this.applicationObjectDto = new ApplicationObjectDto(applicationDevice.getDevice());
        this.type = applicationDevice.getApplicationDeviceType().getTitle();
    }

    public ApplicationDto(ApplicationSoftware applicationSoftware) {
        this.basedApplicationDto = new BasedApplicationDto(applicationSoftware.getApplication());
        this.category = DEVICE_CATEGORY;
        this.applicationObjectDto = new ApplicationObjectDto(applicationSoftware.getSoftware());
        this.type = applicationSoftware.getApplicationSoftwareType().getTitle();
    }
}
