package com.shishkin.mapper;

import com.shishkin.domain.application.enums.ApplicationObjectType;
import com.shishkin.dto.models.NotificationDto;
import com.shishkin.dto.application.ApplicationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationDto valueOf(ApplicationDto application) {
        System.out.println(123);
        ApplicationObjectType objectType = ApplicationObjectType.findByTitle(application.getCategory());
        return new NotificationDto(
                application.getBasedApplicationDto().getExecutor().getTgChatId(),
                application.getBasedApplicationDto().getId(),
                objectType.toString().toLowerCase(),
                application.getBasedApplicationDto().getPriority());
    }
}
