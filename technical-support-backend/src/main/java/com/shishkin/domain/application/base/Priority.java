package com.shishkin.domain.application.base;

import com.shishkin.domain.NamedBasedEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Priority extends NamedBasedEntity {
    private int autoAppointmentHours;
    private int points;
}
