package com.shishkin.repository;

import com.shishkin.domain.employee.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {
}
