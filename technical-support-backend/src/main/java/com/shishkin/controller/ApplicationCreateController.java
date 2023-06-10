package com.shishkin.controller;

import com.shishkin.dto.application.ApplicationCreateModelsDto;
import com.shishkin.dto.application.ApplicationCreateRequestDto;
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
// URL, обрабатываемые контроллером
@RequestMapping("/api/application/create")
public class ApplicationCreateController {
    private final ApplicationCreateService applicationCreateService;

    // Обеспечивает создание заявки
    @PostMapping
    public ApplicationDto create(@RequestBody ApplicationCreateRequestDto applicationCreateRequestDto) {
        return applicationCreateService.create(applicationCreateRequestDto);
    }
    // Обеспечивает получение данных
    // для клиентского приложения,
    // необходимых для создания заявки
    @GetMapping("/models/{initiatorId}")
    public ApplicationCreateModelsDto getModels(@PathVariable Long initiatorId) {
        return applicationCreateService.getModels(initiatorId);
    }
}
