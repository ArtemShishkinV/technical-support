package com.shishkin.repository;

import com.shishkin.domain.application.base.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
    Priority findByTitle(String title);
}
