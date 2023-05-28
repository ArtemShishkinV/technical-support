package com.shishkin.repository;

import com.shishkin.domain.application.software.ApplicationSoftwareType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationSoftwareTypeRepository extends JpaRepository<ApplicationSoftwareType, Long> {
    ApplicationSoftwareType findByTitle(String title);
}
