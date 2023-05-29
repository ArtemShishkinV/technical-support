package com.shishkin.controller;

import com.shishkin.domain.software.Software;
import com.shishkin.domain.software.SoftwareType;
import com.shishkin.repository.SoftwareRepository;
import com.shishkin.repository.SoftwareTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/software")
public class SoftwareController {
    private final SoftwareRepository softwareRepository;
    private final SoftwareTypeRepository softwareTypeRepository;

    @GetMapping("/types")
    public List<SoftwareType> getTypes() {
        return softwareTypeRepository.findAll();
    }

    @GetMapping
    public List<Software> getAll() {
        return softwareRepository.findAll();
    }
}
