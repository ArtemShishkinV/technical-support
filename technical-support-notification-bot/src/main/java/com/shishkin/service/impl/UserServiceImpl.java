package com.shishkin.service.impl;

import com.shishkin.repository.UserRepository;
import com.shishkin.service.UserService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Contact;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public boolean checkRegisterByContact(Contact contact) {
        return userRepository.existsByPhoneNumber(getPhoneNumber(contact));
    }

    @Override
    public boolean checkRegisterByChatId(Long chatId) {
        if (chatId != null)
            return userRepository.existsByTgChatId(chatId.toString());
        return false;
    }

    @Override
    public void register(Contact contact) {
        val user = userRepository.getByPhoneNumber(getPhoneNumber(contact));
        if (user.isPresent()) {
            val parUser = user.get();
            parUser.setTgChatId(contact.getUserId().toString());
            userRepository.save(parUser);
        }
    }

    private String getPhoneNumber(Contact contact) {
        val phoneNumber = contact.getPhoneNumber();
        if (phoneNumber.startsWith("+"))
            return phoneNumber.substring(1);
        return phoneNumber;
    }

}
