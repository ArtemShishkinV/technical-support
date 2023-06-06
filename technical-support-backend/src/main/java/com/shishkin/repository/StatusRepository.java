package com.shishkin.repository;

import com.shishkin.domain.application.base.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByTitle(String title);
}
