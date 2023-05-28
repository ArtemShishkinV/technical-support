package com.shishkin.dto.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.domain.device.Device;
import com.shishkin.domain.software.Software;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class ApplicationObjectDto {
    Long id;
    String title;
    String category;

    //TODO: Можно сделать поле типа json, которое будет содержать всю доп. инфу различную в категориях объекта заявки

    ApplicationObjectDto(Device device) {
        this.id = device.getSerialNumber();
        this.title = device.getTitle();
        this.category = device.getDeviceType().getTitle();
    }

    ApplicationObjectDto(Software software) {
        this.id = software.getId();
        this.title = software.getTitle();
        this.category = software.getSoftwareType().getTitle();
    }
}
