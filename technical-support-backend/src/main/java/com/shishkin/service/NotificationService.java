package com.shishkin.service;

import com.shishkin.dto.application.ApplicationDto;

public interface NotificationService {
    void sendNotification(ApplicationDto applicationDto);
}
