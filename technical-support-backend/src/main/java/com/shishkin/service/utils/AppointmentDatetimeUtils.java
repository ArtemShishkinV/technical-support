package com.shishkin.service.utils;

import com.shishkin.domain.application.Priority;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppointmentDatetimeUtils {
    public static LocalDateTime getAppointmentAt(Priority priority, LocalDateTime current) {
        LocalDateTime withMinutesAppointment = current.plus(priority.getAutoAppointmentHours(), ChronoUnit.HOURS);
        return getNearestHourQuarter(withMinutesAppointment);
    }

    private static LocalDateTime getNearestHourQuarter(LocalDateTime datetime) {
        int minutes = datetime.getMinute();
        int mod = minutes % 15;
        LocalDateTime newDatetime;
        if (mod < 8) {
            newDatetime = datetime.minusMinutes(mod - 1);
        } else {
            newDatetime = datetime.plusMinutes(15 - mod - 1);
        }

        newDatetime = newDatetime.truncatedTo(ChronoUnit.MINUTES);

        return newDatetime;
    }
}
