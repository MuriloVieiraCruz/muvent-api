package com.muvent.api.strategy.userStrategies;

import com.muvent.api.domain.user.User;
import com.muvent.api.domain.user.dto.UserUpdateDTO;

public interface UserUpdateValidation {

    void validate(User user, UserUpdateDTO userUpdateDTO);
}
