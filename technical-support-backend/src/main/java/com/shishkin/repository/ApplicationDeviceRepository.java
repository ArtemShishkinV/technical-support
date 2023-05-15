package com.shishkin.repository;

import com.shishkin.domain.application.device.ApplicationDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationDeviceRepository extends JpaRepository<ApplicationDevice, Long> {
}
