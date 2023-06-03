package com.shishkin.service.impl;

import com.shishkin.domain.employee.Employee;
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
        return userRepository.existsByPhoneNumber(contact.getPhoneNumber());
    }

    @Override
    public boolean register(Contact contact) {
        val user = userRepository.getByPhoneNumber(contact.getPhoneNumber());
        if (user.isPresent()) {
            val parUser = user.get();
            parUser.setTgChatId(contact.getUserId().toString());
            userRepository.save(parUser);
            return true;
        }
        return false;
    }
}
