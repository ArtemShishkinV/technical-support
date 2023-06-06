package com.shishkin.service;

import com.shishkin.dto.application.ApplicationCreateModelsDto;
import com.shishkin.dto.application.ApplicationCreateRequestDto;
import com.shishkin.dto.application.ApplicationDto;

public interface ApplicationCreateService {
    ApplicationDto create(ApplicationCreateRequestDto applicationCreateRequestDto);
    ApplicationCreateModelsDto getModels(Long initiatorId);
}
