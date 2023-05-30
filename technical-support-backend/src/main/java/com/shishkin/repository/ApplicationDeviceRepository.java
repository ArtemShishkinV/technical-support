package com.shishkin.repository;

import com.shishkin.domain.application.device.ApplicationDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationDeviceRepository extends JpaRepository<ApplicationDevice, Long> {
    @Query(
            value = "SELECT * FROM application_device " +
                    "LEFT JOIN application a on a.id = application_device.application_id " +
                    "LEFT JOIN status s on a.status_id = s.id " +
                    "WHERE s.title = 'В работе'",
            nativeQuery = true
    )
    List<ApplicationDevice> findAllActive();

    @Query(
            value = "SELECT * FROM application_device " +
                    "LEFT JOIN application a on a.id = application_device.application_id " +
                    "LEFT JOIN status s on a.status_id = s.id " +
                    "WHERE s.title = 'Создана'",
            nativeQuery = true
    )
    List<ApplicationDevice> findAllNew();

    @Query(
            value = "SELECT * FROM application_device " +
                    "LEFT JOIN application a on a.id = application_device.application_id " +
                    "LEFT JOIN status s on a.status_id = s.id " +
                    "WHERE s.title = 'Отменена' or s.title = 'Решена'",
            nativeQuery = true
    )
    List<ApplicationDevice> findAllArchive();

    ApplicationDevice findByApplicationId(Long id);
}
