package com.shishkin.dto.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.domain.application.ApplicationObjectType;
import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.domain.application.software.ApplicationSoftware;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class ApplicationDto {
    BasedApplicationDto basedApplicationDto;
    String category;
    ApplicationObjectDto applicationObjectDto;
    String type;

    public ApplicationDto(ApplicationDevice applicationDevice) {
        this.basedApplicationDto = new BasedApplicationDto(applicationDevice.getApplication());
        this.category = ApplicationObjectType.DEVICE.getTitle();
        this.applicationObjectDto = new ApplicationObjectDto(applicationDevice.getDevice());
        this.type = applicationDevice.getApplicationDeviceType().getTitle();
    }

    public ApplicationDto(ApplicationSoftware applicationSoftware) {
        this.basedApplicationDto = new BasedApplicationDto(applicationSoftware.getApplication());
        this.category = ApplicationObjectType.SOFTWARE.getTitle();
        this.applicationObjectDto = new ApplicationObjectDto(applicationSoftware.getSoftware());
        this.type = applicationSoftware.getApplicationSoftwareType().getTitle();
    }
}
