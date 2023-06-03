package com.shishkin.domain.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ApplicationObjectType {
    DEVICE("Заявка на технику"),
    SOFTWARE("Заявка на ПО");

    private final String title;

    public static ApplicationObjectType findByTitle(String title) {
        return Arrays.stream(ApplicationObjectType.values())
                .filter(item ->item.title.equals(title))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
