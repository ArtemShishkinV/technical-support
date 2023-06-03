package com.shishkin.dto;

public record NotificationDto(String chatId,
                                     Long applicationId,
                                     String applicationCategory,
                                     String applicationPriority) {
}
