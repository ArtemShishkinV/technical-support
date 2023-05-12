package com.shishkin.domain.device;

import com.shishkin.domain.employee.Employee;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class Device {
    @Id
    private String serialNumber;
    @ManyToOne
    @JoinColumn(name = "deviceType_id", referencedColumnName = "id")
    private DeviceType deviceType;
    @ManyToOne
    @JoinColumn(name = "deviceCondition_id", referencedColumnName = "id")
    private DeviceCondition deviceCondition;
    @ManyToOne
    @JoinColumn(name = "owner_staffNumber", referencedColumnName = "staffNumber")
    private Employee owner;
}
