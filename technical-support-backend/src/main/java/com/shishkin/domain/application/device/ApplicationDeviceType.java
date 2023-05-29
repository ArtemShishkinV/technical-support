package com.shishkin.domain.application.device;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ApplicationDeviceType extends NamedBasedEntity {
    @JsonIgnore
    @OneToMany(mappedBy = "applicationDeviceType")
    private Set<ApplicationDevice> applicationDevices;
}
