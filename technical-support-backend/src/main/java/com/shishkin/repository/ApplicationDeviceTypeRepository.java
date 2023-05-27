package com.shishkin.repository;

import com.shishkin.domain.application.device.ApplicationDeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationDeviceTypeRepository extends JpaRepository<ApplicationDeviceType, Long> {
}
