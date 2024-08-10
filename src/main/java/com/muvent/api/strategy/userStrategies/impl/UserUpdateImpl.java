package com.muvent.api.strategy.userStrategies.impl;

import com.muvent.api.domain.user.User;
import com.muvent.api.domain.user.dto.UserUpdateDTO;
import com.muvent.api.strategy.userStrategies.UserUpdateValidation;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateImpl implements UserUpdateValidation {

    @Override
    public void validate(User user, UserUpdateDTO userUpdateDTO) {
        user.setCpf(userUpdateDTO.cpf());
        user.setEmail(userUpdateDTO.email());
        user.setBirthDate(userUpdateDTO.birthDate());
        user.setFirstName(userUpdateDTO.firstName());
        user.setLastName(userUpdateDTO.lastName());
    }
}
