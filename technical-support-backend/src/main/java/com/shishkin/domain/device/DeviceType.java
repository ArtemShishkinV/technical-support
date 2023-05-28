package com.shishkin.domain.device;

import com.shishkin.domain.NamedBasedEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DeviceType extends NamedBasedEntity {
    private int countReplacementDays;

    @OneToMany(mappedBy = "deviceType")
    private Set<Device> devices;
}
