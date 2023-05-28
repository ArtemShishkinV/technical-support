package com.shishkin.dto.employee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.domain.employee.Office;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
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
