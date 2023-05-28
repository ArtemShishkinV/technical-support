package com.shishkin.repository;

import com.shishkin.domain.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;


public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query(
            value = "SELECT * FROM application " +
                    "WHERE executor_staff_number = ?1",
            nativeQuery = true
    )
    List<Application> findAllByExecutorStaffNumber(Long staffNumber);
}
