package com.shishkin.dto.employee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.shishkin.domain.employee.JobPost;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class JobPostDto {
    Long id;
    String title;

    public JobPostDto(JobPost jobPost) {
        this.id = jobPost.getId();
        this.title = jobPost.getTitle();
    }
}
