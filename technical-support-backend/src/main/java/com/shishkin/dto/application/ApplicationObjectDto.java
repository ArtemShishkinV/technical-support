package com.shishkin.dto.application;

import com.shishkin.domain.device.Device;
import com.shishkin.domain.software.Software;
import lombok.Value;

@Value
class ApplicationObjectDto {
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
