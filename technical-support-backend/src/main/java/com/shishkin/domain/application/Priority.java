package com.shishkin.domain.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priority {
    LOW(48), MEDIUM(24), HIGH(6), CRITICAL(0);
    private final int autoAppointmentHours;
}
