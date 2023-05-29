package com.shishkin.service;

import com.shishkin.dto.application.ApplicationCreateModelsDto;
import com.shishkin.dto.application.ApplicationCreatedDto;
import com.shishkin.dto.application.ApplicationDto;

public interface ApplicationCreateService {
    ApplicationDto create(ApplicationCreatedDto applicationCreatedDto);
    ApplicationCreateModelsDto getModels(Long initiatorId);
}
