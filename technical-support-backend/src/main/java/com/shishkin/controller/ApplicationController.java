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
// URL, обрабатываемые контроллером
@RequestMapping("/api/application")
@AllArgsConstructor
public class ApplicationController {
    // Сервис для работы с заявками
    private final ApplicationService applicationService;

    // Получение всех заявок, которые в работе
    @GetMapping("/active")
    public List<ApplicationDto> findActive() {
        return applicationService.findAllActive();
    }

    // Получение всех заявок, со статусом "Создана"
    @GetMapping("/new")
    public List<ApplicationDto> findNew() {
        return applicationService.findAllNew();
    }

    // Получение всех решенных или отмененных заявок - архивных
    @GetMapping("/archive")
    public List<ApplicationDto> findArchive() {
        return applicationService.findAllArchive();
    }

    // Получение заявки, по категории и уникальному идентификатору
    @GetMapping("/{category}/{id}")
    public ApplicationDto findByCategoryAndId(@PathVariable String category, @PathVariable Long id) {
        return applicationService.findByCategoryAndApplicationId(category, id);
    }

    // Смена статуса заявки
    @PostMapping("/change-status")
    public ApplicationDto changeStatus(@RequestBody ApplicationDto applicationDto) {
        return applicationService.changeStatus(applicationDto);
    }
}
