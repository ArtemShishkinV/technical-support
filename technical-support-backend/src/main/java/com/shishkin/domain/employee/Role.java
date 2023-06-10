package com.shishkin.domain.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum Role  {
    EMPLOYEE("ROLE_EMPLOYEE", "Работник"),
    SUPPORT("ROLE_SUPPORT", "Специалист технической поддержки"),
    ADMIN("ROLE_ADMIN", "Администратор");

    private final String title;
    private final String code;

    public static Role getByCode(String code) {
        return Stream.of(Role.values())
                .filter(role -> role.getCode().equals(code))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }
}
