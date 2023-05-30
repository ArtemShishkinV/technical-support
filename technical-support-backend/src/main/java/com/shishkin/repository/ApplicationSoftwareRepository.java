package com.shishkin.repository;

import com.shishkin.domain.application.software.ApplicationSoftware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationSoftwareRepository extends JpaRepository<ApplicationSoftware, Long> {
    @Query(
            value = "SELECT * FROM application_software " +
                    "LEFT JOIN application a on a.id = application_software.application_id " +
                    "LEFT JOIN status s on a.status_id = s.id " +
                    "WHERE s.title = 'В работе'",
            nativeQuery = true
    )
    List<ApplicationSoftware> findAllActive();

    @Query(
            value = "SELECT * FROM application_software " +
                    "LEFT JOIN application a on a.id = application_software.application_id " +
                    "LEFT JOIN status s on a.status_id = s.id " +
                    "WHERE s.title = 'Создана'",
            nativeQuery = true
    )
    List<ApplicationSoftware> findAllNew();

    @Query(
            value = "SELECT * FROM application_software " +
                    "LEFT JOIN application a on a.id = application_software.application_id " +
                    "LEFT JOIN status s on a.status_id = s.id " +
                    "WHERE s.title = 'Отменена' or s.title = 'Решена'",
            nativeQuery = true
    )
    List<ApplicationSoftware> findAllArchive();

    ApplicationSoftware findByApplicationId(Long id);
}
