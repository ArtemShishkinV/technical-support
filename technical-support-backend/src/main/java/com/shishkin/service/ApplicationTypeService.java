package com.shishkin.service;

import com.shishkin.domain.application.device.ApplicationDevice;
import com.shishkin.domain.application.software.ApplicationSoftware;

public interface ApplicationTypeService {
    ApplicationDevice save(ApplicationDevice applicationDevice);
    ApplicationSoftware save(ApplicationSoftware applicationSoftware);
}
