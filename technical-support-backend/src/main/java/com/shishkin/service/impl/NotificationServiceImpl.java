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

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final NotificationMapper notificationMapper;

    @Value("${notification.url}")
    private String notificationUrl;

    @Override
    public void sendNotification(ApplicationDto application) {
        if (application.getBasedApplicationDto().getExecutor().getTgChatId() != null) {
            String res = WebClient.builder()
                    .build()
                    .post()
                    .uri(notificationUrl)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(notificationMapper.valueOf(application))
                    .retrieve().bodyToMono(String.class).block();
            log.info(res);
        }
    }
}
