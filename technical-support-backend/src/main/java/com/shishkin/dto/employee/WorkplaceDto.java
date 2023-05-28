package com.shishkin.dto.employee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.domain.employee.Workplace;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
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
