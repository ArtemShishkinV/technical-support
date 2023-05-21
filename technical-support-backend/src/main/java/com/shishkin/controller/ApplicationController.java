package com.shishkin.controller;

import com.shishkin.dto.application.ApplicationDto;
import com.shishkin.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/application")
@AllArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    @GetMapping("/active")
    public List<ApplicationDto> findActive() {
        return applicationService.findAllActive();
    }

    @GetMapping("/archive")
    public List<ApplicationDto> findArchive() {
        return applicationService.findAllArchive();
    }
}
