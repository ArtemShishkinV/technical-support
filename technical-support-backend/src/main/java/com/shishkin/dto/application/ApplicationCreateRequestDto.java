package com.shishkin.dto.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.dto.employee.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
// Параметры для конструктора, необходимые для корректной сериализации объекта
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class ApplicationCreateRequestDto {
      // DTO клиента, создавшего заявку
      EmployeeDto initiator;
      // Описание заявки
      String description;
      // Категория заявки - заявка на ПО или заявка на технику
      String category;
      // Зависит от выбранной категории, при выборе заявки на ПО
      // данные выбираются из таблицы application_software_type,
      // а при выборе заявки на технику – application_device_type.
      String type;
      // Приоритет заявки
      String priority;
      // Требуется ли оффлайн-решение
      boolean isOffline;
      // ID объекта заявки(ПО или техники)
      Long applicationObjectId;
}
