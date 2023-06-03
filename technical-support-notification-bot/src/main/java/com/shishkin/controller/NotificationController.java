package com.shishkin.controller;

import com.shishkin.dto.NotificationDto;
import com.shishkin.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notify")
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> notify(@RequestBody NotificationDto notificationDto) {
        log.info(notificationDto.toString());
        notificationService.sendNotification(notificationDto);
        return ResponseEntity.ok("Уведомление отправлено!");
    }
}
