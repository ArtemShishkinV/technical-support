package com.shishkin.controller;

import com.shishkin.dto.application.ApplicationCreatedDto;
import com.shishkin.dto.application.ApplicationDto;
import com.shishkin.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/new")
    public List<ApplicationDto> findNew() {
        return applicationService.findAllNew();
    }

    @GetMapping("/archive")
    public List<ApplicationDto> findArchive() {
        return applicationService.findAllArchive();
    }

    @PostMapping("/create")
    public ApplicationDto create(@RequestBody ApplicationCreatedDto applicationCreatedDto) {
        return applicationService.create(applicationCreatedDto);
    }

    @PostMapping("/change-status")
    public ApplicationDto changeStatus(@RequestBody ApplicationDto applicationDto) {
        return applicationService.changeStatus(applicationDto);
    }
}
