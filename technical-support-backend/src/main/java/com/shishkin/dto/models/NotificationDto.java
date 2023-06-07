package com.shishkin.dto.models;

public record NotificationDto(String chatId,
                                     Long applicationId,
                                     String applicationCategory,
                                     String applicationPriority) {
}
