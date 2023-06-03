package com.shishkin.domain.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationObjectType {
    DEVICE("Заявка на технику"),
    SOFTWARE("Заявка на ПО");

    private final String title;
}
