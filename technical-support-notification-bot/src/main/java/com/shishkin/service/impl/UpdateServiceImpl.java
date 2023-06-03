package com.shishkin.service.impl;

import com.shishkin.service.UpdateService;
import com.shishkin.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
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

    //Обработка сообщений от пользователя в тг боте
    @Override
    public SendMessage process(Update update) {
        Message message = update.getMessage();
        log.info(message.toString());
        //Если chatId человека уже зарегистрирован, то возвращаем сообщение об ожидании уведомлений
        if (userService.checkRegisterByChatId(message.getChatId())) {
            return registerSuccessMessage(message);
        }
        //Проверка на содержание в сообщении контактной инф.(человек поделился данными, нажав на кнопку)
        if (message.hasContact()) {
            //Регистрация в боте, если номер телефона есть в БД
            return handleRegisterIfExist(message);
        }
        //Отправляем человеку сообщение с кнопкой для регистрации
        return createRegisterMessage(message.getChatId());
    }

    private SendMessage handleRegisterIfExist(Message message) {
        //Проверяем есть ли номер телефона в БД
        boolean isRegister = userService.checkRegisterByContact(message.getContact());
        if (isRegister) {
            //Регистрируем человека в боте, сохраняя его ChatId в БД
            return handleRegister(message);
        }
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("Вашего номера нет в базе, обратитесь к специалисту")
                .build();
    }

    private SendMessage handleRegister(Message message) {
        userService.register(message.getContact());
        return registerSuccessMessage(message);
    }

    private SendMessage createRegisterMessage(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text("Нажми чтобы подтвердить регистрацию и получать уведомления о заявках!")
                .replyMarkup(getRegistrationKeyboard())
                .build();
    }

    private SendMessage registerSuccessMessage(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("Ожидайте получения уведомлений...")
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
