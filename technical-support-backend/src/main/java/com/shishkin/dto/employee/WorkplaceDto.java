package com.shishkin.dto.employee;

import com.shishkin.domain.employee.Workplace;
import lombok.Value;

@Value
public class WorkplaceDto {
    Long id;
    int floor;
    String roomNumber;
    int tableNumber;

    public WorkplaceDto(Workplace workplace) {
        this.id = workplace.getId();
        this.floor = workplace.getFloor();
        this.roomNumber = workplace.getRoomNumber();
        this.tableNumber = workplace.getTableNumber();
    }
}
