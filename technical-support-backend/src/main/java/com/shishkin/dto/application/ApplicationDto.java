package com.shishkin.dto.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class ApplicationDto {
    BasedApplicationDto basedApplicationDto;
    String category;
    ApplicationObjectDto applicationObjectDto;
    String type;
}
