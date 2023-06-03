package com.shishkin.domain.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationStatus {
    NEW("Создана"),
    ACTIVE("В работе"),
    SOLVED("Решена"),
    CANCELLED("Отменена");

    private final String title;
}
