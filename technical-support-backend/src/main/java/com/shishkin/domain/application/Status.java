package com.shishkin.domain.application;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    CANCELED("Отменена"),
    EXPIRED("Просрочена"),
    CREATED("Создана"),
    IN_PROGRESS("В работе"),
    COMPLETED("Выполнена");

    private final String title;
}
