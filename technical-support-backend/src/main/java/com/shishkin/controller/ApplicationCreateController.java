package com.shishkin.controller;

import com.shishkin.dto.application.ApplicationCreateModelsDto;
import com.shishkin.dto.application.ApplicationCreatedDto;
import com.shishkin.dto.application.ApplicationDto;
import com.shishkin.service.ApplicationCreateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/application/create")
public class ApplicationCreateController {
    private final ApplicationCreateService applicationCreateService;

    @PostMapping
    public ApplicationDto create(@RequestBody ApplicationCreatedDto applicationCreatedDto) {
        return applicationCreateService.create(applicationCreatedDto);
    }

    @GetMapping("/models/{initiatorId}")
    public ApplicationCreateModelsDto getModels(@PathVariable Long initiatorId) {
        return applicationCreateService.getModels(initiatorId);
    }
}
