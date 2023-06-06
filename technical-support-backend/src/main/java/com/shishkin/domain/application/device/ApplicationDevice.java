package com.shishkin.domain.application.device;

import com.shishkin.domain.BaseEntity;
import com.shishkin.domain.application.base.Application;
import com.shishkin.domain.device.Device;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDevice extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "device_serialNumber", referencedColumnName = "serialNumber")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "applicationDeviceType_id", referencedColumnName = "id")
    private ApplicationDeviceType applicationDeviceType;
}
