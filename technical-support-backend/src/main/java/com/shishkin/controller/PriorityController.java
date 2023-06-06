package com.shishkin.controller;

import com.shishkin.domain.application.base.Priority;
import com.shishkin.repository.PriorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/priority")
@AllArgsConstructor
public class PriorityController {
    private final PriorityRepository priorityRepository;
    @GetMapping
    public List<Priority> getAll() {
        return priorityRepository.findAll();
    }
}
