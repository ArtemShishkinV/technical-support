package com.shishkin.domain.application;

import com.shishkin.domain.NamedBasedEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class Priority extends NamedBasedEntity {
    private int autoAppointmentHours;
}
