package com.shishkin.repository;

import com.shishkin.domain.employee.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long> {
}
