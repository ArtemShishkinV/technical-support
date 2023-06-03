package com.shishkin.repository;

import com.shishkin.domain.application.software.ApplicationSoftware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationSoftwareRepository extends JpaRepository<ApplicationSoftware, Long> {
    @Modifying
    @Query(
            value = "SELECT * FROM application_software asoft " +
                    "LEFT JOIN application a on a.id = asoft.application_id " +
                    "LEFT JOIN status s on a.status_id = s.id " +
                    "WHERE s.title in :titles",
            nativeQuery = true)
    List<ApplicationSoftware> findAllByStatusTitles(@Param("titles") String... titles);

    ApplicationSoftware findByApplicationId(Long id);
}
