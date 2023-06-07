package com.shishkin.repository;

import com.shishkin.domain.device.DeviceCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceConditionRepository extends JpaRepository<DeviceCondition, Long> {
    DeviceCondition findByTitle(String title);
}
