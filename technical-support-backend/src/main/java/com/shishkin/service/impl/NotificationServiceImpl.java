package com.shishkin.service.impl;

import com.shishkin.dto.application.ApplicationDto;
import com.shishkin.mapper.NotificationMapper;
import com.shishkin.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.ConnectException;

@Service
@RequiredArgsConstructor
@Slf4j
// Класс, реализующий отправку запроса на сервис уведомлений
public class NotificationServiceImpl implements NotificationService {
    private final NotificationMapper notificationMapper;

    @Value("${notification.url}")
    private String notificationUrl;

    // Метод, реализующий отправку запроса на сервис уведомления
    @Override
    public void sendNotification(ApplicationDto application) {
        // Проверка, что пользователь зарегистрирован в Telegram боте
        if (application.getBasedApplicationDto().getExecutor().getTgChatId() != null) {
            try {
                // Формирование и отправка асинхронного запроса
                // на сервис уведомлений.
                String res = WebClient.builder()
                        .build()
                        .post()
                        .uri(notificationUrl)
                        .accept(MediaType.APPLICATION_JSON)
                        .bodyValue(notificationMapper.valueOf(application))
                        .retrieve().bodyToMono(String.class).block();
                log.info(res);
            } catch (Exception exception) {
                // Логируем исключение, если что-то пошло не так
                // для того чтобы создание заявки не завершилось ошибкой
                log.warn("Ошибка при отправке запроса на уведомление " + exception.getMessage());
            }
        }
    }
}
