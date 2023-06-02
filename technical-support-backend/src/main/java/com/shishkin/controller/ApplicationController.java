package com.shishkin.controller;

import com.shishkin.dto.application.ApplicationDto;
import com.shishkin.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<ApplicationDto> findActiveByInitiator() {
        return applicationService.findAllActive();
    }

    @GetMapping("/new")
    public List<ApplicationDto> findNewByInitiator() {
        return applicationService.findAllNew();
    }

    @GetMapping("/archive")
    public List<ApplicationDto> findArchiveByInitiator() {
        return applicationService.findAllArchive();
    }

    @GetMapping("/{category}/{id}")
    public ApplicationDto findByCategoryAndId(@PathVariable String category, @PathVariable Long id) {
        return applicationService.findByCategoryAndId(category, id);
    }

    @PostMapping("/change-status")
    public ApplicationDto changeStatus(@RequestBody ApplicationDto applicationDto) {
        return applicationService.changeStatus(applicationDto);
    }
}
