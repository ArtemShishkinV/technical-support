package com.shishkin.domain.device;

import com.shishkin.domain.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial_number_generator")
    @SequenceGenerator(
            name = "serial_number_generator",
            sequenceName = "device_seq",
            initialValue = 100000,
            allocationSize = 1
    )
    private Long serialNumber;

    private String title;
    private String description;

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
