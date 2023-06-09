package com.shishkin.repository;

import com.shishkin.domain.application.base.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Определение интерфейса и наследование от интерфейса JpaRepository<T, ID>,
// где T - класс-сущность, с которым будет работать репозиторий
// ID - тип уникального идентификатора(первичного ключа) в классе-сущности T
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // Пример создания нативного запроса с параметром, аналог классическому PreparedStatement
    @Query(
            value = "SELECT * FROM application " +
                    "WHERE executor_staff_number = ?1",
            nativeQuery = true
    )
    List<Application> findAllByExecutorStaffNumber(Long staffNumber);
    // Пример генерации запроса с помощью репозитория на основании названия,
    // параметров и типа возвращаемого значения метода
    List<Application> findAllByPriority(String priorityTitle);
}
