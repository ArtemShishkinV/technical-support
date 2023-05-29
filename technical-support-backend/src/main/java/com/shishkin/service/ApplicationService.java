package com.shishkin.service;

import com.shishkin.dto.application.ApplicationCreatedDto;
import com.shishkin.dto.application.ApplicationDto;

import java.util.List;

public interface ApplicationService {
    List<ApplicationDto> findAllActive();

    List<ApplicationDto> findAllArchive();

    List<ApplicationDto> findAllNew();



    ApplicationDto changeStatus(ApplicationDto applicationDto);
}
