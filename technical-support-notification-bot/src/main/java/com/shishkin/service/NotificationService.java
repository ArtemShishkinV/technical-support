package com.shishkin.service;

import com.shishkin.dto.NotificationDto;

public interface NotificationService {
    void sendNotification(NotificationDto notificationDto);
}
