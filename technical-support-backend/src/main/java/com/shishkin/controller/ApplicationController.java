package com.shishkin.controller;

import com.shishkin.dto.ApplicationDeviceDto;
import com.shishkin.dto.ApplicationSoftwareDto;
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
    @GetMapping("/devices")
    public List<ApplicationDeviceDto> findAllApplicationDevices() {
        throw new NotYetImplementedException();
    }

    @GetMapping("/software")
    public List<ApplicationSoftwareDto> findAllApplicationSoftware() {
        throw new NotYetImplementedException();
    }
}
