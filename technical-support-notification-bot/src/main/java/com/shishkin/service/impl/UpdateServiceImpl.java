package com.shishkin.service.impl;

import com.shishkin.service.UpdateService;
import com.shishkin.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UpdateServiceImpl implements UpdateService {
    private final UserService userService;

    @Override
    public SendMessage process(Update update) {
        Message message = update.getMessage();
        log.info(message.toString());
        if (message.hasContact()) {
            return handleMessage(message);
        }
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("Нажми чтобы подтвердить регистрацию и получать уведомления о заявках!")
                .replyMarkup(getRegistrationKeyboard())
                .build();
    }

    private SendMessage handleMessage(Message message) {
        boolean isRegister = userService.checkRegisterByContact(message.getContact());
        if (isRegister) {
            userService.register(message.getContact());
            return SendMessage.builder()
                    .chatId(message.getChatId())
                    .text("Ожидайте получения уведомлений...")
                    .build();
        }
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("Вашего номера нет в базе, обратитесь к специалисту")
                .build();
    }

    private ReplyKeyboardMarkup getRegistrationKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>(List.of(new KeyboardRow(
                List.of(getRegistrationButton())
        )));
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    private KeyboardButton getRegistrationButton() {
        val button = new KeyboardButton("Зарегистрироваться!");
        button.setRequestContact(true);
        return button;
    }
}
