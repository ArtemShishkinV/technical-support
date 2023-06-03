package com.shishkin.service.impl;

import com.shishkin.bot.TelegramBot;
import com.shishkin.dto.NotificationDto;
import com.shishkin.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Value("${application.base.link}")
    private String applicationBaseLink;
    private final TelegramBot telegramBot;

    @Override
    public void sendNotification(NotificationDto notificationDto) {
        val message = SendMessage.builder()
                .chatId(notificationDto.chatId())
                .text(getNotificationMessage(notificationDto))
                .replyMarkup(getNotificationKeyboard(notificationDto))
                .build();

        telegramBot.sendMessage(message);
    }

    private InlineKeyboardMarkup getNotificationKeyboard(NotificationDto notificationDto) {
        InlineKeyboardButton applicationLinkButton = new InlineKeyboardButton("Перейти к заявке");
        applicationLinkButton.setUrl(getNotificationLink(notificationDto));

        return new InlineKeyboardMarkup(
                List.of(List.of(applicationLinkButton)));
    }

    private String getNotificationMessage(NotificationDto notificationDto) {
        return String.format(
                "На вас создана заявка с номером - %d, " +
                        "приоритет - %s.%n",
                notificationDto.applicationId(),
                notificationDto.applicationPriority()
        );
    }

    private String getNotificationLink(NotificationDto notificationDto) {
        return applicationBaseLink + "/" + notificationDto.applicationCategory() + "/" + notificationDto.applicationId();
    }
}
