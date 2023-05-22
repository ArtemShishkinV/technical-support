package com.shishkin.domain.application.device;


import com.shishkin.domain.NamedBasedEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class ApplicationDeviceType extends NamedBasedEntity {
    @OneToMany(mappedBy = "applicationDeviceType")
    private Set<ApplicationDevice> applicationDevices;
}