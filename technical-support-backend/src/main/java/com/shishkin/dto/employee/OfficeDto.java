package com.shishkin.dto.employee;

import com.shishkin.domain.employee.Office;
import lombok.Value;

@Value
public class OfficeDto {
    Long id;
    int buildNumber;
    String city;
    String street;

    public OfficeDto(Office office) {
        this.id = office.getId();
        this.buildNumber = office.getBuildNumber();
        this.city = office.getCity();
        this.street = office.getStreet();
    }
}
