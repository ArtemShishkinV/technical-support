package com.shishkin.repository;

import com.shishkin.domain.software.SoftwareType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareTypeRepository extends JpaRepository<SoftwareType, Long> {
}
