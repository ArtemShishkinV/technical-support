package com.shishkin.service;

import org.telegram.telegrambots.meta.api.objects.Contact;

public interface UserService {
    boolean checkRegisterByContact(Contact contact);

    boolean register(Contact contact);
}
