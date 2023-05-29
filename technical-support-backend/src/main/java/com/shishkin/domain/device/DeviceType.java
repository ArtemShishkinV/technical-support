package com.shishkin.domain.device;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shishkin.domain.NamedBasedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JsonIgnore
    @OneToMany(mappedBy = "deviceType")
    private Set<Device> devices;
}
